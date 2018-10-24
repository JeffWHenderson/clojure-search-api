(ns search-api.config
  (:require [environ.core :refer [env]]))

(defn get-solr-host []
  (env :solr-host))

(defn get-search-core []
  (env :search-core))

