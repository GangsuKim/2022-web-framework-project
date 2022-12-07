package com.example.crawl;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// import java.net.URLDecoder;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;

public class market {
    public static void main(String[] args) {
        ArrayList<String[]> itemList = lotte("꼬깔콘");

        for(String[] item: itemList) {
            System.out.println("===== New item =====");
            System.out.println("상품명 : " + item[0]);
            System.out.println("가격 : " + item[1]);
            System.out.println("단가 : " + item[2]);
            System.out.println("사진 URL : " + item[3]);
        }
    }

    /**
     * 이마트의 상품 정보 크롤링
     * @param item 문자열 형태의 상품명
     * @return ArrayList 형태 [["상품명", "상품 가격", "상품 단가", "상품 사진 경로"],...]
     */
    static ArrayList<String[]> emart(String item){
        ArrayList<String[]> itemList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("https://emart.ssg.com/search.ssg?target=all&query=" + URLEncoder.encode(item, "UTF-8") + "&shpp=ssgem").userAgent("Mozilla").get();

            Element items = doc.getElementById("area_searchItemList"); // 아이템이 있는 Div 가져오기
            Elements itemDivs = items.getElementsByClass("mnemitem_goods"); // 상품 각각에 대해서 불러오기
    
            for(Element e: itemDivs) {
                if(e.text().contains("쓱배송")) {
                    String name = e.getElementsByClass("mnemitem_goods_tit").get(0).text(); // Item name
                    String price = e.getElementsByClass("ssg_price").get(0).text(); // Item price
                    String unitPrice = e.getElementsByClass("unit_price").get(0).text(); // Item unit price
                    String imageUrl = e.getElementsByClass("mnemitem_thmb_img").attr("src"); // Item image path
    
                    String[] tempArr = {name, price, unitPrice, "https:" + imageUrl};
    
                    itemList.add(tempArr);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemList;
    }

    /**
     * 홈플러스의 상품 정보 크롤링
     * @param item 문자열 형태의 상품명
     * @return ArrayList 형태 [["상품명", "상품 가격", "상품 단가", "상품 사진 경로"], ... ]
     */
    static ArrayList<String[]> homeplus(String item) {
        ArrayList<String[]> itemList = new ArrayList<>();

        try {
            String uriItem = URLEncoder.encode(item, "UTF-8");
            String url = "https://front.homeplus.co.kr/express/search/item.json?addSubCategoryYn=Y&inputKeyword=" + uriItem + "&originalSearchYn=N&page=1&perPage=40&searchKeyword=" + uriItem + "&searchType=NONE&sort=RANK";
            String doc = Jsoup.connect(url).userAgent("Mozilla").ignoreContentType(true).execute().body();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(doc);

            JSONObject data = (JSONObject) jsonObject.get("data");

            JSONArray dataArray = (JSONArray) data.get("dataList");

            if(dataArray.size() > 0) {
                for (int i = 0; i < dataArray.size(); i++) {
                    JSONObject obj = (JSONObject) dataArray.get(i);
                    String name = (String) obj.get("itemNm");
                    String price = Long.toString((long) obj.get("salePrice"));

                    String unitQty = Long.toString((long) obj.get("unitQty"));
                    String unitMeasure = (String) obj.get("unitMeasure");
                    String unitPrice = Double.toString((double) obj.get("unitPrice"));
                    String itemNo = (String) obj.get("itemNo");

                    String unitInfos = unitQty + unitMeasure + " 당 " + unitPrice + "원";
                    String imageUrl = "https://image.homeplus.kr/it/" + itemNo + "s0270";
                    String[] tempArr = {name, price, unitInfos, imageUrl};
    
                    itemList.add(tempArr);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException p) {
            p.printStackTrace();
        }

        return itemList;
    }

    /**
     * 롯데마트의 상품 정보 크롤링
     * @param item 문자열 형태의 상품명
     * @return ArrayList 형태 [["상품명", "상품 가격", "상품 단가", "상품 사진 경로"], ... ]
     */
    static ArrayList<String[]> lotte(String item) {
        ArrayList<String[]> itemList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("https://www.lotteon.com/search/search/search.ecn?&u2=0&u3=60&render=qapi&platform=pc&collection_id=301&q=" + URLEncoder.encode(item, "UTF-8") + "&x_param=&mallId=4&u7=qd&u16=ranking.desc").userAgent("Mozilla").get();
            Elements items = doc.getElementsByClass("srchProductItem"); // 아이템이 있는 Div 가져오기
            // System.out.println(items);
            // Elements itemDivs = items.getElementsByClass("mnemitem_goods"); // 상품 각각에 대해서 불러오기
    
            for(Element e: items) {
                if(e.text().contains("바로배송")) {
                    String name = e.getElementsByClass("srchProductUnitTitle").get(0).text(); // Item name
                    String price = e.getElementsByClass("s-product-price__number").get(0).text(); // Item price
                    String unitPrice = e.getElementsByClass("s-product-price__unit").text(); // Item unit price
                    String imageUrl = e.getElementsByTag("img").get(0).attr("src"); // Item image path
    
                    String[] tempArr = {name, price, unitPrice, imageUrl};
    
                    itemList.add(tempArr);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemList;
    }
}
