dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "childre"
    password = "sUPuteP6"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:mysql://198.199.86.140:3306/childre_qu?useUnicode=true&characterEncoding=UTF-8"
            username = "childre"
            password = "sUPuteP6"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://198.188.86.140::3306/childre_qu?useUnicode=true&characterEncoding=UTF-8"
            username = "childre"
            password = "sUPuteP6"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost::3306/childre_prod?useUnicode=true&characterEncoding=UTF-8"
            username = "childre"
            password = "sUPuteP6"
            pooled = true
            properties {
                maxActive = -1
                minEvictableIdleTimeMillis=1800000
                timeBetweenEvictionRunsMillis=1800000
                numTestsPerEvictionRun=3
                testOnBorrow=true
                testWhileIdle=true
                testOnReturn=true
                validationQuery="SELECT 1"
            }
        }
    }
}