# Products-API

This is a standard RESTFULL API using the Richardson model with SPRING BOOT 3.2.2 and java 17, to make easy deployment I used docker to build imagens, and docker-compose to managed and deployment this API.

# Instalation
To install this API you need to have docker install in your worksapce, if you have clone this repository and run the docker_compose.yml.

# Usage

The API is available in the follow address http://217.196.62.11:8181/products, to test you need a API Platform like postman:

POST METHOD

URI http://217.196.62.11:8181/products

http status</br>
2xx Created</br>
5xx Internal Server Error</br>

Body JSON

	{
	    "name": "Product name",
	    "valeu": 100.00
	}

RESPONSE

	{
	    "idProduct": "UUID",
	    "name": "Loren Ipsum",
	    "value": 5500.00
	}


GET METHOD all products

URI http://217.196.62.11:8181/products

http status</br>
2xx Ok</br>
4xx Not Found</br>
5xx 5xx Internal Server Error</br>

Response

	[
	    {
	        "idProduct": "UUID",
	        "name": "Loren Ipsum",
	        "value": 5500.00,
	        "links": [
	            {
	                "rel": "self",
	                "href": "http://217.196.62.11:8181/products/UUID"
	            }
	        ]
	    }
	]

GET METHOD one product

URI http://217.196.62.11:8181/products/UUID

http status</br>
2xx Ok</br>
4xx Not Found</br>
5xx Internal Server Error</br>

RESPONSE

	{
	    "idProduct": "UUDI",
	    "name": "Loren Ipsum",
	    "value": 5000.00,
	    "_links": {
	        "self": {
	            "href": "http://localhost:8181/products"
	        }
	    }
	}

PUT METHOD

URI http://217.196.62.11:8181/products/UUID<br>

http status</br>
2xx Ok</br>
4xx Not Found</br>
5xx Internal Server Error</br>

RESPONSE

	{
	    "idProduct": "UUID",
	    "name": "Loren Ipsum",
	    "value": 4400.00
	}

DELETE METHOD

URI http://217.196.62.11:8181/products/UUID

http status</br>
2xx Ok</br>
4xx Not Found</br>
5xx Internal Server Error</br>

RESPONSE

Product deleted successfully.
