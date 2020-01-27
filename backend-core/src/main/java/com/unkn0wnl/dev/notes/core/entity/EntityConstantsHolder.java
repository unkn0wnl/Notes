package com.unkn0wnl.dev.notes.core.entity;

public final class EntityConstantsHolder {

    private EntityConstantsHolder() {
        throw new IllegalStateException("You can't create an instance of this class.");
    }

    public final static class UserConstants {
        public static final int MAX_NAME_LENGTH = 40;
        public static final int MAX_EMAIL_LENGTH = 40;
        public static final int MAX_USERNAME_LENGTH = 15;
        public static final int MAX_PASSWORD_LENGTH = 100;

        private UserConstants() {
        }
    }

    public final static class RoleConstants {
        public static final int ROLE_NAME_LENGTH = 20;

        private RoleConstants() {
        }
    }

}