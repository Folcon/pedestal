; Copyright 2013 Relevance, Inc.

; The use and distribution terms for this software are covered by the
; Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0)
; which can be found in the file epl-v10.html at the root of this distribution.
;
; By using this software in any fashion, you are agreeing to be bound by
; the terms of this license.
;
; You must not remove this notice, or any other, from this software.

(ns io.pedestal.app.util.test
  (:require [io.pedestal.app :as app]
            [io.pedestal.app.util.platform :as platform]))

(defn run-sync!
  ([app script]
     (run-sync! app script 1000))
  ([app script timeout]
     (app/run! app script)
     (loop [timeout timeout]
       (when (pos? timeout)
         (if (= (-> app :state deref :input) (last script))
           true
           (do (Thread/sleep 20)
               (recur (- timeout 20))))))))
