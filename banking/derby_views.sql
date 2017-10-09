--------------------------------------------------------
--  File created - Saturday-September-30-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for View BANK_STATEMENT_V
--------------------------------------------------------

  CREATE OR REPLACE VIEW BANK_STATEMENT_V (FYE, WHO, BANK, USERS) AS select distinct fye, who, bank, users from bank_transaction order by fye desc, who
--------------------------------------------------------
--  DDL for View CAT_TRANS_V
--------------------------------------------------------

CREATE OR REPLACE VIEW CAT_TRANS_V (CATEGORY, SEARCH_STR, WHEN, DESCRIPTION, AMOUNT, WHO, FYE, BANK, ID) AS select lnk.category, lnk.SEARCH, bt.WHEN,bt.DESCRIPTION,bt.AMOUNT,bt.WHO,bt.FYE,bt.BANK,bt.ID 
from bank_transaction bt
LEFT OUTER JOIN cat_trans_lnk lnk on (bt.DESCRIPTION LIKE lnk.SEARCH||%)
order by 1
--------------------------------------------------------
--  DDL for View TRANSACTION_SUMMARY
--------------------------------------------------------

  CREATE OR REPLACE VIEW TRANSACTION_SUMMARY (TYPE, CATEGORY, USERS, FYE, WHO, AMOUNT, TAX_EXPENSE) AS select TYPE,CATEGORY,USERS,FYE,WHO,AMOUNT,TAX_EXPENSE from (
select c.TYPE, c.NAME as category, c.users, ct.fye, ct.who, sum(ct.AMOUNT) as amount, sum(ct.amount*c.DEDUCTION) as tax_expense
from cat_trans_v ct, category c
where ct.CATEGORY = c.NAME
group by c.type, c.name, c.users, ct.fye, ct.who
union all
select c.TYPE, TOTAL as category, c.users, ct.fye, ct.who, sum(ct.AMOUNT) as amount, sum(ct.amount) as tax_expense
from cat_trans_v ct, category c
where ct.CATEGORY = c.NAME
and c.users = Glenn Jennie
group by c.type, c.users, ct.fye, ct.who
) order by type, category, who
