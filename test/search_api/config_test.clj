(ns search-api.config-test
  (:require [midje.sweet :refer :all]
            [environ.core :refer [env]]
            [search-api.config :refer :all]))


(facts "about application runtime configuration"
       (fact "reads SOLR_HOST from environment"
             (get-solr-host) => "somethings funny"
             (provided (env :solr-host) => "somethings funny"))
       (fact "reads CORE from the environment"
             (get-search-core) => "search core"
             (provided (env :search-core) => "search core")))
