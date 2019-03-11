package com.example.libbase.binding.adapter.recyclerview;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.libbase.binding.command.BindingCommand;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.subjects.PublishSubject;

/**
 * @author ChenQiuE
 * Date：2019/3/11 15:17
 * Email：1077503420@qq.com
 */
public class RecyclerViewBindingAdapter {

    @BindingAdapter("lineManager")
    public static void setLineManager(RecyclerView recyclerView, LineManagers.LineManagerFactory lineManagerFactory) {
        recyclerView.addItemDecoration(lineManagerFactory.create(recyclerView));
    }


    @BindingAdapter(value = {"onScrollChangeCommand", "onScrollStateChangedCommand"}, requireAll = false)
    public static void onScrollChangeCommand(final RecyclerView recyclerView,
                                             final BindingCommand<ScrollDataWrapper> onScrollChangeCommand,
                                             final BindingCommand<Integer> onScrollStateChangedCommand) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int state;

            @Override
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (onScrollChangeCommand != null) {
                    onScrollChangeCommand.execute(new ScrollDataWrapper(dx, dy, state));
                }
            }

            @Override
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                state = newState;
                if (onScrollStateChangedCommand != null) {
                    onScrollStateChangedCommand.execute(newState);
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    @BindingAdapter({"onLoadMoreCommand"})
    public static void onLoadMoreCommand(final RecyclerView recyclerView, final BindingCommand<Integer> onLoadMoreCommand) {
        RecyclerView.OnScrollListener listener = new OnScrollListener(onLoadMoreCommand);
        recyclerView.addOnScrollListener(listener);

    }

    @BindingAdapter("itemAnimator")
    public static void setItemAnimator(RecyclerView recyclerView, RecyclerView.ItemAnimator animator) {
        recyclerView.setItemAnimator(animator);
    }

    public static class OnScrollListener extends RecyclerView.OnScrollListener {

        private PublishSubject<Integer> methodInvoke = PublishSubject.create();

        private BindingCommand<Integer> onLoadMoreCommand;

        public OnScrollListener(final BindingCommand<Integer> onLoadMoreCommand) {
            this.onLoadMoreCommand = onLoadMoreCommand;
            methodInvoke.throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(integer -> onLoadMoreCommand.execute(integer));
        }

        @Override
        public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            assert layoutManager != null;
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int pastVisiablesItems = layoutManager.findFirstVisibleItemPosition();
            if ((visibleItemCount + pastVisiablesItems) >= totalItemCount) {
                if (onLoadMoreCommand != null) {
                    methodInvoke.onNext(Objects.requireNonNull(recyclerView.getAdapter()).getItemCount());
                }
            }
        }

        @Override
        public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }
    }

    public static class ScrollDataWrapper {
        public float scrollX;
        public float scrollY;
        public int state;

        public ScrollDataWrapper(float scrollX, float scrollY, int state) {
            this.scrollX = scrollX;
            this.scrollY = scrollY;
            this.state = state;
        }
    }
}
