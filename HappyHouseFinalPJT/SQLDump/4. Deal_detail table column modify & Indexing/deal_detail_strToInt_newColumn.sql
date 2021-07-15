ALTER TABLE deal_detail CHANGE dealAmount dealAmount2 VARCHAR(30);
ALTER TABLE deal_detail ADD dealAmount INT;

UPDATE deal_detail SET dealAmount = cast(CONCAT(SUBSTRING_INDEX(dealAmount2, ',', 1), SUBSTRING_INDEX(SUBSTRING_INDEX(dealAmount2, ',', 2), ',', -1)) AS UNSIGNED)

# happyhouseDB create -> happyhouse Migration 수행 후 
# deal_detail 에서 dealAmount 컬럼이 String 인데 이를 int 형으로 바꿔주기 위해서 dealAmount를 임시로 dealAmount2로 컬럼명을 바꾸고 int type dealAmount 컬럼을 만들어서 위 sql을 수행하여 데이터 변경
