(ns search-api.config-test
  (:require [midje.sweet :refer :all]
            [environ.core :refer [env]]
            [search-api.config :refer :all]))


(facts "about application runtime configuration"
       (fact "reads SOLR_HOST from environment"
             (get-solr-host) => "somethings funny"
             (provided (env :solr-host) => "somethings funny"))
       (fact "reads SEARCH_CORE from the environment"
             (get-search-core) => "search core"
             (provided (env :search-core) => "search core"))
       (fact "throws exception when SOLR_HOST is not present"
             (get-solr-host) => (throws RuntimeException)
             (get-search-core) => (throws RuntimeException)))
