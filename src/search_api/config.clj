(ns search-api.config
  (:require [environ.core :refer [env]])
  (:import [java.lang.RuntimeException]))

(defn get-solr-host []
  (let [host-name (env :solr-host)]
    (if (nil? host-name) (throw (RuntimeException.)))
    host-name))

(defn get-search-core []
  (let [core-name (env :search-core)]
    (if (nil? core-name) (throw (RuntimeException.)))
    core-name))

