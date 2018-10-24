(ns search-api.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]

            [ring.util.response :refer [response]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]])
  (:import
    (org.apache.solr.client.solrj.impl XMLResponseParser HttpSolrClient$Builder)
    (org.apache.solr.client.solrj SolrQuery)))

(def solr-host
  (search-api.config/get-solr-host))

(def solr-core
  (search-api.config/get-search-core))

(defn solr-client []
  (let [builder (HttpSolrClient$Builder. (str solr-host "/" solr-core))
        client (.build builder)
        parser (XMLResponseParser.)]
    (.setParser client parser)
    client))

(defn solr-query [term]
  (.query (solr-client) (SolrQuery. term)))

(defroutes app-routes
           (GET "/" [] (response {:message "Hello Jeff and Vishwas"}))
           (GET "/search" [q] (response {:results (map #(select-keys % ["title" "date"]) (.getResults (solr-query q)))}))
           (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (wrap-json-body {:keywords? true :bigdecimals? true})
      (wrap-json-response))
  )
