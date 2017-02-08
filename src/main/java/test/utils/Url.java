package test.utils;

/**
 * Created by ilya on 08/02/2017.
 */
public enum Url {
    MAIN {
        public String toString() {
            return "http://localhost/litecart/";
        }
    },

    ADMIN {
        public String toString() {
            return "http://localhost/litecart/admin/";
        }
    }

}
