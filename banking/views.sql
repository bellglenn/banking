--------------------------------------------------------
--  File created - Tuesday-October-10-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for View BANK_STATEMENT_V
--------------------------------------------------------

  CREATE OR REPLACE VIEW "BANK_STATEMENT_V" ("FYE", "WHO", "BANK", "TRAN_COUNT") AS select fye, who, bank, count(*) as tran_count 
from bank_transaction a
group by fye, who, bank
order by who, bank
--------------------------------------------------------
--  DDL for View BANK_TRANSACTION
--------------------------------------------------------

  CREATE OR REPLACE VIEW "BANK_TRANSACTION" ("WHEN", "DESCRIPTION", "AMOUNT", "WHO", "FYE", "BANK", "ID") AS select "WHEN","DESCRIPTION","AMOUNT","WHO","FYE","BANK","ID" from bank_transaction_t bt
where exists (select 1 from current_fye_users c where c.users like '%'||bt.who||'%' and bt.fye = c.fye) order by when
--------------------------------------------------------
--  DDL for View CAT_TRANS_LNK
--------------------------------------------------------

  CREATE OR REPLACE VIEW "CAT_TRANS_LNK" ("CATEGORY", "SEARCH", "USERS", "FYE") AS select "CATEGORY","SEARCH","USERS","FYE" from cat_trans_lnk_t a
where exists (select 1 from current_fye_users c where a.users = c.users and a.fye = c.fye)
order by search
--------------------------------------------------------
--  DDL for View CAT_TRANS_V
--------------------------------------------------------

  CREATE OR REPLACE VIEW "CAT_TRANS_V" ("CATEGORY", "SEARCH", "WHEN", "DESCRIPTION", "AMOUNT", "WHO", "FYE", "BANK", "ID") AS select lnk.category, lnk.SEARCH, bt."WHEN",bt."DESCRIPTION",bt."AMOUNT",bt."WHO",bt."FYE",bt."BANK",bt."ID"
from bank_transaction bt
LEFT OUTER JOIN cat_trans_lnk lnk on (bt.DESCRIPTION LIKE lnk.SEARCH||'%')
order by 1
--------------------------------------------------------
--  DDL for View CATEGORY
--------------------------------------------------------

  CREATE OR REPLACE VIEW "CATEGORY" ("NAME", "TYPE", "DEDUCTION", "USERS", "SORT_ORDER", "FYE") AS select "NAME","TYPE","DEDUCTION","USERS","SORT_ORDER","FYE" from category_t  a 
where exists (select 1 from current_fye_users c where a.users = c.users and a.fye = c.fye)
order by name
--------------------------------------------------------
--  DDL for View CURRENT_FYE_USERS
--------------------------------------------------------

  CREATE OR REPLACE VIEW "CURRENT_FYE_USERS" ("FYE", "USERS", "SELECTED") AS select "FYE","USERS","SELECTED" from current_fye_users_t where selected = 'Y'
--------------------------------------------------------
--  DDL for View TRANSACTION_SUMMARY
--------------------------------------------------------

  CREATE OR REPLACE VIEW "TRANSACTION_SUMMARY" ("TYPE", "CATEGORY", "FYE", "WHO", "AMOUNT", "TAX") AS select c.TYPE, ct.category, ct.fye, ct.who, sum(ct.AMOUNT) as amount, decode(round(sum(ct.amount*c.DEDUCTION), 2), 0, null, round(sum(ct.amount*c.DEDUCTION))) as tax
from cat_trans_v ct, category c
where ct.CATEGORY = c.NAME
and exists (select 1 from current_fye_users curr where curr.fye = ct.fye and curr.users like '%'||ct.who||'%')
group by c.type, ct.category, ct.fye, ct.who
order by c.type, ct.category, ct.who
--------------------------------------------------------
--  DDL for View TRANSACTION_SUMMARY_GRP
--------------------------------------------------------

  CREATE OR REPLACE VIEW "TRANSACTION_SUMMARY_GRP" ("TYPE", "CATEGORY", "FYE", "WHO", "AMOUNT", "TAX") AS select c.TYPE, ct.category, ct.fye, c.users as who, sum(ct.AMOUNT) as amount, 
decode(round(sum(ct.amount*c.DEDUCTION), 2), 0, null, round(sum(ct.amount*c.DEDUCTION))) as tax
from cat_trans_v ct, category c
where ct.CATEGORY = c.NAME
and exists (select 1 from current_fye_users curr where curr.fye = ct.fye and curr.users like '%'||ct.who||'%')
group by c.type, ct.category, ct.fye, c.users
order by type, category
