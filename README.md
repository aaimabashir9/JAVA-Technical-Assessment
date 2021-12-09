# engineers-assignment
--------------------------------------------------------------------------------------

# API Task

# Task Title
    Implement an API that fullfil the business requirments for topuping a wallet.

## Task descriptioin
 - Create a spring boot application with default controller to receive the below request and do the validation needed then save it into a DB before sending the respoonse back.
 - DB preferred MangoDB/Postgress DB.
 
## Topup request
   - topup request must contain the below information,
   
| Field               | Type     | Existence  |  Description |
|---                  |---       |---         |---           |
|  amount             | Decimal  | Mandatory  | Topup amount  |
|  currency           | String   | Mandatory  | Amount currency | 
|  charge_id          | String   | Mandatory  | Assume amount is already deducted from the card holder on a previouse charge |
|  customer           | Object   | Mandatory  | Customer who topup his wallet|
|  customer.id        | String   | Mandatory  | custome id |
|  customer.wallet_id | String   | Mandatory  | In case of customer has multiple wallets, so he must define the wallet id to be filled   |
|  fees               | Object   | Optional   | Any fees can be taken by the merchant for giving service to ther customer   |
|  fees.amount        | Decimal  | Mandatory  | Fees amouunt  |
|  fees.curency       | String   | Mandatory  | Fees currency   |


## Topuup response
   - topup response must contain the below information,
   
| Field                     | Type     | Existence  |  Description |
|---                        |---       |---         |---           |
|  id                       | UUID     | Mandatory  | unique ID generated each for each request  |
|  created                  | timestamp| Mandatory  |              |
|  status                   | String   | Mandatory  | can be one of this list (INITIATED-SUCCESS-FAILED)  |
|  amount                   | Decimal  | Mandatory  |   |
|  currency                 | String   | Mandatory  |  | 
|  charge_id                | String   | Mandatory  |  |
|  customer                 | Object   | Mandatory  | |
|  customer.id              | String   | Mandatory  |   |
|  customer.wallet_id       | String   | Mandatory  |   |
|  fees                     | Object   | Optional   |   |
|  fees.amount              | Decimal  | Mandatory  |   |
|  fees.curency             | String   | Mandatory  |   |
|  balance                  | Object   | Mandatory  |    |
|  balance.total_amount     | Decimal  | Mandatory  |  increase current value with  (amount - fees )  |

    Do not push your code here, instead push to your github and invite me.
