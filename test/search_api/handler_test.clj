(ns search-api.handler-test
  (:require [midje.sweet :refer :all]
            [ring.mock.request :as mock]
            [search-api.handler :refer :all]))

(defn mock-get [path]
  (app (mock/request :get path)))

(facts "about rest end points"
       (fact "route /invalid returns 404"
             (let [response (mock-get "/invalid")]
               (:status response) => 404))
       (fact (mock-get "/") => (contains {:status 200
                                          :body   "{\"message\":\"Hello Jeff and Vishwas\"}"}))
       (fact (mock-get "/search?q=California") => (contains {:status 200})
             (provided (solr-query "California") => []))
       (fact (mock-get "/search?q=California") => (contains {:body "{\"results\":[{\"title\":\"Something great\",\"date\":\"Today\"}]}"})
             (provided (solr-query "California") => [{"title" "Something great"
                                                      "date"  "Today"}]))
       (fact (mock-get "/search?q=California") => (contains {:status 567})
             (provided (handle-query "California") => {:status 567})))


