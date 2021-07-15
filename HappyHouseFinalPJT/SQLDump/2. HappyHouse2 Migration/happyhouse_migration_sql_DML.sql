INSERT INTO happyhouse2.sido(sido_code, sido_name)
SELECT CODE, NAME
FROM happyhouse.sido_code;
-- 시도


INSERT INTO happyhouse2.gu(gu_code, gu_name, sido_code)
SELECT code, NAME, sido_code
FROM happyhouse.gugun_code;
-- 구


INSERT INTO happyhouse2.dong(dong_code, dong_name, gu_code, sido_code)
SELECT code, NAME, gugun_code, city_code
FROM happyhouse.dong_code;
-- 동

INSERT INTO happyhouse2.apart(code, NAME, jibun, lat, lng, buildyear, dong_code)
SELECT NO, aptName, jibun, lat, lng, buildyear, d.code
FROM happyhouse.houseinfo h, happyhouse.dong_code d
WHERE h.dong like d.name AND h.code = d.gugun_code;
-- apart


INSERT INTO happyhouse2.deal(code, FLOOR, AREA, TYPE, apart_code)
SELECT hd.NO, FLOOR, AREA, TYPE, IFNULL(hi.no, 5989)
FROM happyhouse.housedeal hd LEFT OUTER JOIN happyhouse.houseinfo hi
ON hd.AptName = hi.AptName AND hd.code = hi.code AND hd.buildYear = hi.buildyear AND hd.jibun = hi.jibun AND hd.dong = hi.dong;
-- deal table  , 기존 제공된 테이블에 housedeal 거래 정보에는 존재하는 아파트가 houseinfo에는 없는 경우가 몇개 있어서 left outer join으로 모든 정보를 뽑아주고 apart_code가 null인 경우는 임시로 5989 번으로 넣었다.


INSERT INTO happyhouse2.deal_detail(code, dealyear, dealmonth, dealday, dealAmount)
SELECT NO, dealyear, dealmonth, dealday, dealAmount
FROM happyhouse.housedeal;
-- deal_detail table


INSERT INTO group_code
VALUES(100, "사용자그룹"),
		(200, "매물그룹"),
		(300, "위치그룹"),
		(400, "선호그룹");
-- group code


INSERT INTO code(group_code, code, about)
VALUES(100, 'ADM', "관리자"),
		(100, 'REG', "정회원"),
		(100, 'ASO', "준회원"),
		(200, 'DEL', "deal"),
		(200, 'APT', "apart"),
		(300, 'SID', "sido"),
		(300, 'GUC', "gu"),
		(300, 'DON', "dong"),
		(400, 'CE7', "카페"),
		(400, 'SC4', "학교"),
		(400, 'CS2', "편의점"),
		(400, 'SW8', "지하철");
		

INSERT INTO code(group_code, code, about)
VALUES(200, 'TST', "테스트");
-- code table