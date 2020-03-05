(defproject md-archiviofarmaco "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [seancorfield/next.jdbc "1.0.13"]
                 [cprop "0.1.16"]
                 [honeysql "0.9.8"]
                 [org.postgresql/postgresql "42.2.9"]
                 [com.zaxxer/HikariCP "3.4.2"]
                 [cambium/cambium.core "0.9.3"]
                 [cambium/cambium.codec-simple "0.9.3"]
                 [cambium/cambium.logback.core "0.4.3"]
                 [camel-snake-kebab "0.4.1"]]
  :main ^:skip-aot md-archiviofarmaco.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
