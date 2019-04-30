package com.example.register;

import com.example.libbase.console.SNResponseEntity;

/**
 * 注册
 * @author ChenQiuE
 * Date：2019/4/30 14:49
 * Email：qiue.chen@supernovachina.com
 */
public class RegisterLoginEntity extends SNResponseEntity {

    private DataEntity data;

    public DataEntity getData() {
        return data;
    }

    public class DataEntity{
        private String id;
        private String password;
        private String token;
        private String type;
        private String username;

        public String getId() {
            return id;
        }

        public String getPassword() {
            return password;
        }

        public String getToken() {
            return token;
        }

        public String getType() {
            return type;
        }

        public String getUsername() {
            return username;
        }
    }
}
