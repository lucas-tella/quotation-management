Feature: List registered quotes

    Background: 
        * url 'http://localhost:8081/'
        
    Scenario: Get registered quotes
      Given path 'quotations'
      When method GET
      Then status 200
        
    Scenario: Get registered quotes
   		Given path 'stock'
   		When method GET
   		Then status 200