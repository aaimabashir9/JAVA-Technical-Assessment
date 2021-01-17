# engineers-onboarding-assignments
Assignments dedicated for onboarding new engineers

# Part1 Algorithm Task

# Task Title
Write efficient function takes stock prices yesterday returns best profit

## Task descriptioin
Suppose we could access yesterday’s stock prices as a list, where:
 - The indices are the time in minutes past trade opening time, which was 9:30am local time.
 - The values are the price in dollars of Apple stock at that time.
 
 So if the stock cost $500 at 10:30am, stock_prices_yesterday[60] = 500. Write an efficient function that takes stock_prices_yesterday and returns the best    profit I could have made from 1 purchase and 1 sale of 1 Apple stock yesterday.

For example:stock_prices_yesterday = [10, 7, 5, 8, 11, 9]
    # get_max_profit(stock_prices_yesterday)
   returns 6 (buying for $5 and selling for $11)

    Please clone the current repo and push the answer to branch algorithm

------------------------------------------------------------------------------------------------------------------------------------------------------------------

# Part2 Architecture Task

# Task Title
    Implement a full module that send a request and receive a respone. try to implement all the layers [Controller - Service - Repository, ...]

## Task descriptioin
 Create a spring boot application that can construct a request and send it to an external Rest API and receive the respone as below. Also store the response into a DB preferred MangoDB/Postgress DB.

     Please use SOAP not REST

# XML request:
    <?xml version="1.0" encoding="UTF-8"?>
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
       xmlns:com="http://common.ws.xyz.company.com/"
       xmlns:cus="http://common.ws.xyz.company.com/">
    <soapenv:Header>
       <com:identity>
          <com:appID>0000000000000000000</com:appID>
          <com:instName>acmesdsdco</com:instName>
       </com:identity>
    </soapenv:Header>
    <soapenv:Body>
       <cus:register>
          < cus:clientFields>
             <cus:bank_client_number>1234545545455</cus:bank_client_number>
             <cus:member_ica>123456</cus:member_ica>
             <cus:accept_email_message_switch>Y</cus:accept_email_message_switch>
             <cus:account_holder_address_line_1>168 Dongal Building</cus:account_holder_address_line_1>
             <cus:account_holder_address_line_2>Heuston South Quarter</cus:account_holder_address_line_2>
             <cus:account_holder_address_line_3>Apartmernt 12-D</cus:account_holder_address_line_3>
             <cus:account_holder_address_line_4>Attn: Building Manager</cus:account_holder_address_line_4>
             <cus:birth_date>1981-01-18</cus:birth_date>
             <cus:business_phone_number>3143451243</cus:business_phone_number>
             <cus:city_name>St. Louis</cus:city_name>
             <cus:country_code>USA</cus:country_code>
             <cus:email_address>keith.jackson@test.com</cus:email_address>
             <cus:employee_switch>N</cus:employee_switch>
             <cus:fax_phone_number>3143451200</cus:fax_phone_number>
             <cus:generic_identification>Yellow</cus:generic_identification>
             <cus:generic_identification_description>Favorite Color</cus:generic_identification_description>
             <cus:home_phone_number>6363000966</cus:home_phone_number>
             <cus:language_code>en_US</cus:language_code>
             <cus:mobile_phone_number>3146160501</cus:mobile_phone_number>
             <cus:mother_maiden_name>Dolly</cus:mother_maiden_name>
             <cus:postal_code>63043</cus:postal_code>
             <cus:primary_account_holder_first_name>Keith</cus:primary_account_holder_first_name>
             <cus:primary_account_holder_last_name>Jackson</cus:primary_account_holder_last_name>
             <cus:ssn_last_four>1001</cus:ssn_last_four>
             <cus:state>MO</cus:state>
             <cus:user_defined_1>User 1</cus:user_defined_1>
             <cus:user_defined_2>User 2</cus:user_defined_2>
             <cus:user_defined_3>User 3</cus:user_defined_3>
             <cus:user_defined_4>User 4</cus:user_defined_4>
             <cus:vip_indicator>N</cus:vip_indicator>
          </cus:clientFields>        
          <cus:clientAccountFields>
             <cus:bank_account_number>5420000000001234000</cus:bank_account_number>
             <cus:account_status_code>1</cus:account_status_code>
             <cus:bank_product_code>0123450100</cus:bank_product_code>
             <cus:program_identifier>ACMECORR</cus:program_identifier>
             <cus:account_opened_date>20181101</cus:account_opened_date>
             <cus:accrue_points_sw>Y</cus:accrue_points_sw>
             <cus:dda_account_number>5420000000008888000</cus:dda_account_number>
             <cus:enrollment_date>20181201</cus:enrollment_date>
             <cus:ghost_account_number>5420000000009999000</cus:ghost_account_number>
             <cus:household_eligibility_token>00000000000000304412 </cus:household_eligibility_token>
             <cus:partner_id>SPECIAL_PARTNER</cus:partner_id>
             <cus:primary_account_switch>Y</cus:primary_account_switch>
             <cus:receive_statements>Y</cus:receive_statements>
             <cus:reporting_category>SPECIAL_USAGE</cus:reporting_category>
             <cus:user_defined_1>Custom Data 1</cus:user_defined_1>
             <cus:user_defined_2>Custom Data 2</cus:user_defined_2>
             <cus:user_defined_3>Custom Data 3</cus:user_defined_3>
             <cus:user_defined_4>Custom Data 4</cus:user_defined_4>
             <cus:user_defined_5>Custom Data 5</cus:user_defined_5>
             <cus:user_defined_6>Custom Data 6</cus:user_defined_6>
             <cus:user_defined_7>Custom Data 7</cus:user_defined_7>
             <cus:user_defined_8>Custom Data 8</cus:user_defined_8>
          </cus:clientAccountFields>        
       </cus:register>
    </soapenv:Body>
# XML response:

    <registerResponse xmlns=http://xyz.company/ xmlns:com="http://xyz.company/">
     <linkId>00440051940048202401</linkId>
     <idi>44123440</id>
    </registerResponse>


    Please clone the current repo and push the answer to branch ArchitectureTask
