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
            url = "jdbc:mysql://childrenlab.com/childre_qu?useUnicode=true&characterEncoding=UTF-8"
            username = "root"
            password = "koe7POut"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://childrenlab.com/childre_qu?useUnicode=true&characterEncoding=UTF-8"
            username = "childre"
            password = "koe7POut"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://childrenlab.com/childre_prod?useUnicode=true&characterEncoding=UTF-8"
            username = "childre"
            password = "koe7POut"
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