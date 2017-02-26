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
            return "http://localhost/litecart/admin/";}
    },
    ADMIN_COUNTRIES{
        public String toString () {
            return "http://localhost/litecart/admin/?app=countries&doc=countries";}
    },
    ADMIN_GEO_ZONES{
        public String toString () {
            return "http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones";}
    },
    ADMIN_CATALOG{
        public String toString () {
            return "http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1";}
    },
    ADMIN_CUSTOMERS{
        public String toString () {
            return "http://localhost/litecart/admin/?app=customers&doc=customers";}
    }
}
