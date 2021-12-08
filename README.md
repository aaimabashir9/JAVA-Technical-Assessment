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

    Do not push your code here, instead push to your github and invite me.

------------------------------------------------------------------------------------------------------------------------------------------------------------------

# Part2 API Task

# Task Title
    Implement an API that fullfil the business requirments for topuping a wallet.

## Task descriptioin
 - Create a spring boot application with default controller to receive the below request and do the validation needed then save it into a DB before sending the respoonse back.
 - DB preferred MangoDB/Postgress DB.
 
## Topup request
   - topup request must contain the below information,
      - amount       (Decimal - Mandatory).  Topup amount
      - currency     (String - Mandatory).   Amount currency  
      - charge_id    (String - Mandatory).   assume amount is already deducted from the card holder on a previouse charge 
      - customer     (Object - Mandatory).   Customer who topup his wallet 
          - id        (String - Mandatory)
          - wallet_id (String - Mandatory).  may be customer has multiple wallets, so he must define the wallet id to be filled  
      - fees         (Object - Optional).    any fees can be taken by the merchant for providing this service to ther customer
          - amount    (Decimal - Mandatory).  
          - currency  (Decimal - Mandatory)
      - meta         (Object - Optional).     any extra information can be passed here. 
## Topuup response
   - topup response must contain the below information,
     - id (unique ID generated from the DB)
     - created         (timestamp)
     - status          (String)    can be one of this list (INITIATED-SUCCESS-FAILED)  
     - amount          (decimal)
     - currency        (String)
     - charge_id       (String)
     - customer        (Object)
        - id           (String)
        - wallet_id    (String)
     - fees (Object)
        - amount       (Decimal)
        - currency     (Decimal)
     - meta            (Object)
     -  balance        (Object)    Balance changes after the topup.    
       - net_available (decimal)   
       - net_amount    (decimal)
       - total_amount (decimal)
        
    Do not push your code here, instead push to your github and invite me.
