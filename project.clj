(defproject search-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]
                 [org.apache.solr/solr-solrj "7.4.0"]
                 [environ "1.1.0"]]
  :plugins [[lein-ring "0.12.4"]
            [lein-midje "3.2.1"]]
  :ring {:handler search-api.handler/app}
  :profiles

  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]
                        [midje "1.9.1"]]
         :plugins      [[lein-midje "3.2.1"]]}})
