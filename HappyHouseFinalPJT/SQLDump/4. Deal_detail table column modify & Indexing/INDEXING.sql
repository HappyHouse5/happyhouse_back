ALTER TABLE apart ADD INDEX dong(dong_code);
ALTER TABLE deal ADD INDEX area(area);
ALTER TABLE deal ADD INDEX apt(apart_code);
ALTER TABLE deal_detail ADD INDEX deal(dealAmount);



ALTER TABLE deal DROP INDEX AREA;
ALTER TABLE deal_detail DROP INDEX deal;
ALTER TABLE apart DROP INDEX dong;
ALTER TABLE deal DROP INDEX apt;