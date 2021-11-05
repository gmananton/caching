# Spring-caching

The app emulates:

* API for getting currencies exchange rates
* Rates are being stored in-memory and are periodically updated with some random amount
* There's a decorator that emulates slow database/external service, so each request may be processed slowly
* Caching is aimed to speed up requests for non-changing rates
* As soon as data in storage is updated the cache is updated as well to provide fresh data
* Load-testing can be performed using https://artillery.io/