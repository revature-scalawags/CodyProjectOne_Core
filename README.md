# **Cody P's Project One**
# 2020 Wikipedia Clickstream Analysis
Utilizes Hadoop and Hive to process Wikipedia data that has been MapReduced by a separate program (CodyProjectOne MapReduce)

## Requirements
- JDK version 8 or 11 (https://adoptopenjdk.net/).

- Scala and SBT (https://www.scala-lang.org/download/2.12.8.html).

## Usage
1. Start program in the shell with:

    sbt --error run

2. Once program loads, enter a number between 1 and 12 to run a Hive query that will find the top ten links on Wikipedia that were clicked in a particular month of 2020. User may just enter "2020" to see results for the entire year. The results listed will be clicks that led from one Wikipedia page to another.
    - Example: Entering "1" will return the top 10 links clicked on in January 2020, along with displaying the site that the link was present on when clicked, and the destination site that this link led to.
    - User may also specify flags "-from" or "-to" after their input number (**only supported for individual months**)
        - "-from" will return the total amount of clicks on links **from** a particular Wikipedia page, **regardless of where that clicked link led to.**
        - "-to" will return the total amount of clicks that **led to** a particular Wikipedia page, **regardless of which Wikipedia page the user was on when they clicked the link.**

## Features
- Hadoop HDFS
- Hadoop YARN
- Hadoop MapReduce
- Hive
