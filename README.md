# 1. SQL 

Design simplified database model, which would allow to handle following entities: 

- bank account of a client, 

- operations on account being either positive transfer (incomming) or negative (outgoing).

Rules are:
- single client may have multiple accounts

The goal is to model database tables and associations between them and to write down SQL queries which will give you following results:

a) all clients who are older than 30 years old and who didn't have any operations on their account in January 2015

b) name and surnames of all clients, who in 1st quarter of 2015 had the sum of incomming transfers exceeding $100k

c) client with the highest turnover in whole 2015


# 2. Simple OO modelling (Scala)

We have a `class Car` with following attributes:

- String: brand 
- String: model
- String: displacement volume of the engine (always in a form `X.X` where `X` is a character within range: `0-9`)

and method

`Seq[Car] function(Seq[Car] cars)`

You need to write the body of the method which will return `Seq` of elements which:

- will be duplicate-free (no duplicates)
- AND will be sorted by brand, model and displacement volume (in this particular order).



# 3. Poker (Scala FP appreciated)
You have a method which gets set of 5 cards with following signature:

`? check (? cards)`

The goal of this method is to verify which of the common Poker hands is represented within given set ( see here: https://en.wikipedia.org/wiki/List_of_poker_hands#Hand_categories ). 
You need to figure out:

* how single card will be represented

* what will this method return (number? enum? case class? and be ready to explain why you picked following return value)

* how input set of cards will be represented. 

You may create as many types / classes as you need and as many helper methods as needed.

---

Tasks 2 and 3 need to be covered with unit tests. Picking right unit test library (and build system) is your choice.
