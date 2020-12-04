# 接口定义

## 服务器配置

* appid: wxf2fecfe6f8a6c244
* AppSecret: 487826296aa5bf6ba48daa8a9e9c57c4
* ip: 106.54.206.184
* port: 80
* 服务器地址(URL): http://106.54.206.184:80/wx
* 令牌(Token): P511GW5
* EncodingAESKey: WVGipMdBopYOCWd7HNGG8cElR9hVXkHwjYL2cstFa0T

## 接口定义列表

GET 请求的Request拼接在URI上
POST请求的Request Body组装成JSON格式

### 验证性接口

####服务器签名验证
GET http://106.54.206.184:80/wx

Request:
```json
{
    "signature": "",
    "timestamp": "",
    "nonce": "",
    "echostr": ""
}
```
Response:
```json

```
#### Access Token 
GET https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&
appid=APPID&secret=APPSECRET

Request:
```json
{
    "grant_type": "",
    "appid": "",
    "secret": "",
    "echostr": ""
}
```
Response:
```json
{
    "access_token": "",
    "expires_in": ""
}
```
Error:
```json
{
    "errcode": "",
    "errmsg": ""
}
```

#### 微信服务器IP
GET https://api.weixin.qq.com/cgi-bin/get_api_domain_ip?access_token=ACCESS_TOKEN

Request:
```json
{
    "access_token": ""
}
```
Response:
```json
{
    "ip_list": []
}
```
Error:
```json
{
    "errcode": "",
    "errmsg": ""
}
```

#### 长链接转成短链接
POST https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN

Request:
```json
{
    "access_token": "",
    "action": "",
    "long_url": ""
}
```
Response:
```json
{
    "errcode":0,
    "errmsg":"ok",
    "short_url":"http:\/\/w.url.cn\/s\/AvCo6Ih"
}
```
Error:
```json
{
    "errcode": 40013,
    "errmsg": "invalid appid"
}
```

### 用户管理

#### 获取用户基本信息(UnionID机制)
GET https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&
openid=OPENID&lang=zh_CN

Request:
```json
{
    "access_token": "",
    "openid": "",
    "lang": ""
}
```
Response:
```json
{
    "subscribe": 1, 
    "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M", 
    "nickname": "Band", 
    "sex": 1, 
    "language": "zh_CN", 
    "city": "广州", 
    "province": "广东", 
    "country": "中国", 
    "headimgurl":"http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
    "subscribe_time": 1382694957,
    "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
    "remark": "",
    "groupid": 0,
    "tagid_list":[128,2],
    "subscribe_scene": "ADD_SCENE_QR_CODE",
    "qr_scene": 98765,
    "qr_scene_str": ""
}
```
Error:
```json
{
    "errcode": "",
    "errmsg": ""
}
```

#### 批量获取用户信息
POST https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN

Request:
```json
{
    "user_list": [
        {
            "openid": "otvxTs4dckWG7imySrJd6jSi0CWE", 
            "lang": "zh_CN"
        }, 
        {
            "openid": "otvxTs_JZ6SEiP0imdhpi50fuSZg", 
            "lang": "zh_CN"
        }
    ]
}
```
Response:
```json
{
   "user_info_list": [
       {
           "subscribe": 1, 
           "openid": "otvxTs4dckWG7imySrJd6jSi0CWE", 
           "nickname": "iWithery", 
           "sex": 1, 
           "language": "zh_CN", 
           "city": "揭阳", 
           "province": "广东", 
           "country": "中国", 

           "headimgurl": "http://thirdwx.qlogo.cn/mmopen/xbIQx1GRqdvyqkMMhEaGOX802l1CyqMJNgUzKP8MeAeHFicRDSnZH7FY4XB7p8XHXIf6uJA2SCunTPicGKezDC4saKISzRj3nz/0",

          "subscribe_time": 1434093047, 
           "unionid": "oR5GjjgEhCMJFyzaVZdrxZ2zRRF4", 
           "remark": "", 

           "groupid": 0,
           "tagid_list":[128,2],
           "subscribe_scene": "ADD_SCENE_QR_CODE",
           "qr_scene": 98765,
           "qr_scene_str": ""

      }, 
       {
           "subscribe": 0, 
           "openid": "otvxTs_JZ6SEiP0imdhpi50fuSZg"
       }
   ]
}
```
Error:
```json
{
    "errcode": "",
    "errmsg": ""
}
```

#### 获取用户列表
GET https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID

Request:
```json
{
    "access_token": "",
    "next_openid": ""
}
```
Response:
```json
{
    "total":2,
    "count":2,
    "data":{
    "openid":["OPENID1","OPENID2"]},
    "next_openid":"NEXT_OPENID"
}
```
Error:
```json
{
    "errcode": "",
    "errmsg": ""
}
```

#### 获取黑名单
POST https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token=ACCESS_TOKEN

Request:
```json
{
  "begin_openid":"OPENID1"
}
```
Response:
```json
{
 "total":23000,
 "count":10000,
 "data":{
    "openid":[
       "OPENID1",
       "OPENID2",
       ...,
       "OPENID10000"
    ]
  },
  "next_openid":"OPENID10000"
}
```
Error:
```json
{
    "errcode": "",
    "errmsg": ""
}
```

#### 拉黑用户
POST https://api.weixin.qq.com/cgi-bin/tags/members/batchblacklist?access_token=ACCESS_TOKEN

Request:
```json
{
    "openid_list":["OPENID1”,” OPENID2”]
}
```
Response:
```json
{
  "errcode": 0,
  "errmsg": "ok"
}
```
Error:
```json
{
    "errcode": 40013,
    "errmsg": "invalid appid"
}
```

#### 取消拉黑用户
POST https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist?access_token=ACCESS_TOKEN

Request:
```json
{
    "openid_list":["OPENID1”,” OPENID2”]
}
```
Response:
```json
{
  "errcode": 0,
  "errmsg": "ok"
}
```
Error:
```json
{
    "errcode": 40013,
    "errmsg": "invalid appid"
}
```


### 消息管理

#### 用户关注消息接口

用户关注后微信服务器推送过来的请求:

Request:
```xml
<xml>
  <ToUserName><![CDATA[toUser]]></ToUserName>
  <FromUserName><![CDATA[FromUser]]></FromUserName>
  <CreateTime>123456789</CreateTime>
  <MsgType><![CDATA[event]]></MsgType>
  <Event><![CDATA[subscribe]]></Event>
</xml>
```


### 素材管理



### 账号管理



### 数据统计

#### 获取用户增减数据
POST https://api.weixin.qq.com/datacube/getusersummary?access_token=ACCESS_TOKEN

Request:
```json
{ 
    "begin_date": "2014-12-02", 
    "end_date": "2014-12-07"
}
```
Response:
```json
{ 
    "list": [ 
        { 
            "ref_date": "2014-12-07", 
            "user_source": 0, 
            "new_user": 0, 
            "cancel_user": 0
        }//后续还有ref_date在begin_date和end_date之间的数据
    ]
}   
```
Error:
```json
{
    "errcode": 40013,
    "errmsg": "invalid appid"
}
```

#### 获取获取累计用户数据
POST https://api.weixin.qq.com/datacube/getusercumulate?access_token=ACCESS_TOKEN

Request:
```json
{ 
    "begin_date": "2014-12-02", 
    "end_date": "2014-12-07"
}
```
Response:
```json
{ 
    "list": [ 
        { 
            "ref_date": "2014-12-07", 
            "cumulate_user": 1217056
        }, //后续还有ref_date在begin_date和end_date之间的数据
    ]
} 
```
Error:
```json
{
    "errcode": 40013,
    "errmsg": "invalid appid"
}
```

#### 获取公众号返佣商品数据
GET https://api.weixin.qq.com/publisher/stat?action=publisher_cps_general&
access_token=ACCESS_TOKEN

Request:
```json
{ 
    "page": "", 
    "page_size": "",
    "start_date": "2014-12-07",
    "end_date": "2014-12-07"
}
```
Response:
```json
{
    "base_resp":{
        "err_msg":"ok",
        "ret":0
    },
    "list":[
        {
            "date":"20XX-XX-XX",
            "exposure_count":15153,
            "click_count":396,
            "click_rate":38.265151515,
            "order_count":3,
            "order_rate":0.007575758,
            "total_fee":162900,
            "total_commission":25021
        }
    ],
    "summary":{
        "exposure_count":117143,
        "click_count":3442,
        "click_rate":34.033410808,
        "order_count":18,
        "order_rate":0.005229518,
        "total_fee":924082,
        "total_commission":133030
    },
    "total_num":7
}
```
Error:
```json
{
    "errcode": 40013,
    "errmsg": "invalid appid"
}
```

#### 获取公众号结算收入数据
GET https://api.weixin.qq.com/publisher/stat?action=publisher_settlement&
access_token=ACCESS_TOKEN


Request:
```json
{ 
    "page": "", 
    "page_size": "",
    "start_date": "2014-12-07",
    "end_date": "2014-12-07"
}
```
Response:
```json
{
    "base_resp":{
        "err_msg":"ok",
        "ret":0
    },
    "body":"深圳市腾讯计算机系统有限公司",
    "penalty_all":0,
    "revenue_all":5178368698,
    "settled_revenue_all":2613696765,
    "settlement_list":[
        {
            "date":"2020-03-25",
            "zone":"2020年3月1日至15日",
            "month":"202003",
            "order":1,
            "sett_status":1,
            "settled_revenue":718926045,
            "sett_no":"XXX",
            "mail_send_cnt":"0",
            "slot_revenue":[
                {
                    "slot_id":"SLOT_ID_WEAPP_BANNER",
                    "slot_settled_revenue":34139443
                },
                {
                    "slot_id":"SLOT_ID_WEAPP_REWARD_VIDEO",
                    "slot_settled_revenue":684786602
                }
            ]
        }
    ],
    "total_num":1
}
```
Error:
```json
{
    "errcode": 40013,
    "errmsg": "invalid appid"
}
```
