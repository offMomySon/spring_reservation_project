# ProjectC_Reservation

this is the reservation project
fitst step is main page



# Rest Api specification

## 1. Category information

### 1.1. Request
`GET  http://{host_url}/reservation/api/categories`
### Parameter
`No parameters.`
    
### 1.2. Response
#### KEY
| Name | Type | Description |
|:---:|:---:|:---:|
|id    | long | 카테고리의 id |
|name  |string| 카테고리의 이름 |
|count | long | 카테고리 제품 개수 |
#### Response Body
```
{
  "items": [
    {
      "id": 1,
      "name": "전시",
      "count": 10
    },
    {
      "id": 2,
      "name": "뮤지컬",
      "count": 10
    },
    {
      "id": 3,
      "name": "콘서트",
      "count": 16
    },
    {
      "id": 4,
      "name": "클래식",
      "count": 10
    },
    {
      "id": 5,
      "name": "연극",
      "count": 13
    }
  ]
}
```
#### Response Header
```
content-type: application/json;charset=UTF-8 
 date: Thu, 10 Sep 2020 16:07:07 GMT 
 transfer-encoding: chunked 

```

### 1.3. Error Message    
| HTTP 코드	| 에러코드 | 에러메세지 |
|:---:|:---:|:---:|
|404 | error-0005 | server cannot fount category. |


## 2. Promotion information

### 2.1. Request
`GET  http://{host_url}/api/promotions
`
### Parameter
`No parameters.`
    
### 2.2. Response
#### KEY
| Name | Type | Description |
|:---:|:---:|:---:|
|id    | long | 카테고리의 id |
|name  |string| 카테고리의 이름 |
|count | long | 카테고리 제품 개수 |
#### Response Body
```
{
  "items": [
    {
      "id": 1,
      "productId": 1,
      "productImageUrl": "img/1_th_1.png"
    },
    {
      "id": 2,
      "productId": 5,
      "productImageUrl": "img/5_th_13.png"
    },
    {
      "id": 3,
      "productId": 6,
      "productImageUrl": "img/6_th_18.png"
    },
    {
      "id": 4,
      "productId": 9,
      "productImageUrl": "img/9_th_24.png"
    },
    {
      "id": 5,
      "productId": 11,
      "productImageUrl": "img/11_th_30.png"
    },
    {
      "id": 6,
      "productId": 12,
      "productImageUrl": "img/12_th_32.png"
    },
    {
      "id": 7,
      "productId": 18,
      "productImageUrl": "img/18_th_46.png"
    },
    {
      "id": 8,
      "productId": 22,
      "productImageUrl": "img/22_th_55.png"
    },
    {
      "id": 9,
      "productId": 34,
      "productImageUrl": "img/34_th_85.png"
    },
    {
      "id": 10,
      "productId": 41,
      "productImageUrl": "img/41_th_105.png"
    },
    {
      "id": 11,
      "productId": 44,
      "productImageUrl": "img/44_th_112.png"
    }
  ]
}
```
#### Response Header
```
content-type: application/json;charset=UTF-8 
 date: Fri, 11 Sep 2020 20:58:21 GMT 
 transfer-encoding: chunked 

```

### 2.3. Error Message    
| HTTP 코드	| 에러코드 | 에러메세지 |
|:---:|:---:|:---:|
|404 | error-0008 | promotion api result is not exist. |


## 3. Get product list

### 3.1. Request
`http://{host_url}/api/products?categoryId={categoryId}&start={start}`
### Parameter
| Name | Type | Description |
|:---:|:---:|:---:|
|categoryId | integer | 카테고리의 id |
|start  |integer| 시작 위치 |

### 3.2. Response
#### KEY
| Name | Type | Description |
|:---:|:---:|:---:|
|totalCount | integer | 카테고리에 속해있는 product 의 갯수 |
|displayInfoId | integer | product 를 광고(display)하는 정보의 id  |
|productDescription | string | 제품 간략 설명 |
|placeName  | string | 제품 전시 위치 |
|productContent  | string | 제품의 내용 |
|productImageUrl  | string | 제품을 화면에 보여줄 이미지 |

#### Response Body
```
{
  "totalCount": 10,
  "items": [
    {
      "displayInfoId": 1,
      "productId": 1,
      "productDescription": "Paper, Present:너를 위한 선물",
      "placeName": "대림미술관",
      "productContent": "대림미술관은 오는 12월 7일부터 2018년 5월 27일까지 세계적인 아티스트들의 섬세한 감각과 아날로그적 소재인 종이가 만나 감성적인 매체로 확장되는 과정을 소개하는 전시 〈PAPER, PRESENT: 너를 위한 선물〉을 개최합니다. 이번 전시에서는 다양한 분야에서 활동하고 있는 아티스트들이 종이의 본래적 속성에 집중하여 재료 자체의 순수한 아름다움을 담은 작품들을 일곱 개의 감각적인 공간에서 선보입니다. 바람, 별빛, 햇살과 같은 자연 요소와 기억, 설렘과 같은 감정의 요소를 종이와 결합하여 구성한 공간들은, 자연 현상을 감각적으로 경험하고 아날로그적 정서를 자극하는 매체로서 종이를 경험하게 하며 종이에 감성을 입혀 예술로 만나는 특별한 시간을 선물할 것입니다.",
      "productImageUrl": "img/1_th_1.png"
    },
    {
      "displayInfoId": 2,
      "productId": 2,
      "productDescription": "퀀틴 블레이크",
      "placeName": "KT&G 상상마당 홍대 갤러리 (4, 5F)",
      "productContent": "KT&G 상상마당은 20세기 거장시리즈 기획전 일환으로 전 세계적으로 사랑 받는 영국 최고의 일러스트레이터 퀀틴 블레이크(Quentin Blake, 1932-) 전시를 오는 10월 21일(토)부터 내년 2월 20일(화)까지 서울 서교동에 위치한 KT&G 상상마당 갤러리(4,5F)에서 진행한다. 영국 작가 로알드 달(Roald Dahl, 1916-1990)의 아동 소설 『찰리와 초콜릿 공장』 의 원화 작가로 유명한 퀀틴 블레이크는 지난 60여 년간 편안한 그림체와 성인들도 공감할 수 있는 동화들을 꾸준히 발표해왔다. 이번 전시에는 원화 작가에서 나아가 글과 그림을 통한 스토리텔링에 뛰어난 아티스트로서의 면모를 조명하고, 『찰리와 초콜릿 공장』 원화를 비롯한 퀀틴 블레이크의 작품 180여 점과 그의 작업실을 재현해낸 공간을 선보인다.",
      "productImageUrl": "img/2_th_3.png"
    },
    {
      "displayInfoId": 3,
      "productId": 3,
      "productDescription": "ALICE : Into The Rabbit Hole",
      "placeName": "서울숲 갤러리아포레 The Seouliteum G층 (B2)\n",
      "productContent": "<반 고흐 인사이드>, <클림트 인사이드>에 이은\n㈜미디어앤아트의 여섯 번째 아트 프로젝트 <ALICE : into the rabbit hole>은\n루이스 캐럴의 <이상한 나라의 앨리스>, <거울나라의 앨리스> 시리즈를\n현대적인 시각으로 표현해낸 새로운 컨셉의 전시입니다.\n\n동화의 새 패러다임을 열며 전 세계인들의 사랑을 받은 불멸의 명작,\n루이스 캐럴의 ‘앨리스’ 시리즈. 그동안은 책, 애니메이션과 영화 등 2차원 세계에서만\n만나볼 수 있던 이 매력적인 동화의 세계관이 현대적인 감각으로 재해석되어\n예쁘고, 즐겁고, 행복해지는 3차원 테마파크 ‘앨리스 랜드’를 만나보세요.\n\n개성 넘치는 일러스트레이션 작가, 감각적인 뮤지션, 키치한 설치작가와\n대중문화를 선도하는 영상크루 등 총 23팀이 ㈜미디어앤아트와 만나\n저마다의 ‘앨리스’와 ‘원더랜드’를 만들어냈습니다.\n\n아티스트 팀들과 협업을 통해 감상의 한계를 뛰어넘어\n앨리스의 기상천외한 모험을 표현해낸 이번 전시는\n딱딱한 일상에서 탈출하는 신나고 즐거운 경험이 될 것입니다.",
      "productImageUrl": "img/3_th_9.png"
    },
    {
      "displayInfoId": 4,
      "productId": 4,
      "productDescription": "99% 디자인 엑스포",
      "placeName": "코엑스 Hall B",
      "productContent": "99% 디자인 엑스포는 국내 최초로 대학이 주최하는 예술 디자인 박람회 입니다.\n99% 디자인은 '사용자에 의해 (by the people)완성되는 디자인'을 뜻하며\n소수만 누리는 디자인이 아닌 '모두를 위한 (for the people)디자인'을 지향합니다.",
      "productImageUrl": "img/4_th_11.png"
    }
  ]
} 
```
#### Response Header
```
content-type: application/json;charset=UTF-8 
 date: Fri, 11 Sep 2020 20:58:21 GMT 
 transfer-encoding: chunked 

```

### 3.3. Error Message    
| HTTP 코드	| 에러코드 | 에러메세지 |
|:---:|:---:|:---:|
|404 | error-0008 | promotion api result is not exist. |

## 4. Get display info

### 4.1. Request
`http://{host_url}/api/products/{displayInfoId}`
### Parameter
| Name | Type | Description |
|:---:|:---:|:---:|
|displayInfoId  | integer | 전시정보의 id |

### 4.2. Response
#### KEY


#### Response Body
```
{
  "averageScore": 0,
  "comments": [
    {
      "comment": "string",
      "commentId": 0,
      "commentImages": [
        {
          "contentType": "string",
          "createDate": "2020-09-11T21:59:56.603Z",
          "deleteFlag": true,
          "fileId": 0,
          "fileName": "string",
          "imageId": 0,
          "modifyDate": "2020-09-11T21:59:56.603Z",
          "reservationInfoId": 0,
          "reservationUserCommentId": 0,
          "saveFileName": "string"
        }
      ],
      "createDate": "2020-09-11T21:59:56.603Z",
      "modifyDate": "2020-09-11T21:59:56.603Z",
      "productId": 0,
      "reservationDate": "2020-09-11T21:59:56.603Z",
      "reservationEmail": "string",
      "reservationInfoId": 0,
      "reservationName": "string",
      "reservationTelephone": "string",
      "score": 0
    }
  ],
  "displayInfo": {
    "categoryId": 0,
    "categoryName": "string",
    "createDate": "2020-09-11T21:59:56.603Z",
    "displayInfoId": 0,
    "email": "string",
    "homepage": "string",
    "modifyDate": "2020-09-11T21:59:56.603Z",
    "openingHours": "string",
    "placeLot": "string",
    "placeName": "string",
    "placeStreet": "string",
    "productContent": "string",
    "productDescription": "string",
    "productEvent": "string",
    "productId": 0,
    "telephone": "string"
  },
  "displayInfoImage": {
    "contentType": "string",
    "createDate": "2020-09-11T21:59:56.603Z",
    "deleteFlag": true,
    "displayInfoId": 0,
    "displayInfoImageId": 0,
    "fileId": 0,
    "fileName": "string",
    "modifyDate": "2020-09-11T21:59:56.603Z",
    "saveFileName": "string"
  },
  "productImages": [
    {
      "contentType": "string",
      "createDate": "2020-09-11T21:59:56.603Z",
      "deleteFlag": true,
      "fileInfoId": 0,
      "fileName": "string",
      "modifyDate": "2020-09-11T21:59:56.603Z",
      "productId": 0,
      "productImageId": 0,
      "saveFileName": "string",
      "type": "ma"
    }
  ],
  "productPrices": [
    {
      "createDate": "2020-09-11T21:59:56.603Z",
      "discountRate": 0,
      "modifyDate": "2020-09-11T21:59:56.603Z",
      "price": 0,
      "priceTypeName": "string",
      "productId": 0,
      "productPriceId": 0
    }
  ]
}
```
#### Response Header
```
content-type: application/json;charset=UTF-8 
 date: Fri, 11 Sep 2020 20:58:21 GMT 
 transfer-encoding: chunked 

```

### 4.3. Error Message    
| HTTP 코드	| 에러코드 | 에러메세지 |
|:---:|:---:|:---:|
|400 | error-0007 | The parameter entered is invalid.at displayinfo method |
|404 | error-0004 | wrong DisplayInfoRs result |

## 5. inquiry Reservation information

### 5.1. Request
`GET http://{host_url}/api/reservations?reservationEmail={reservationEmail }`
### Parameter
| Name | Type | Description |
|:---:|:---:|:---:|
|reservationEmail | string | 예약정보를 찾아올 이메일 |

### 5.2. Response
#### Response Body
```
{
  "reservations": [
    {
      "cancelYn": true,
      "createDate": "2020-09-11T22:30:34.095Z",
      "displayInfo": {
        "categoryId": 0,
        "categoryName": "string",
        "createDate": "2020-09-11T22:30:34.095Z",
        "displayInfoId": 0,
        "email": "string",
        "homepage": "string",
        "modifyDate": "2020-09-11T22:30:34.095Z",
        "openingHours": "string",
        "placeLot": "string",
        "placeName": "string",
        "placeStreet": "string",
        "productContent": "string",
        "productDescription": "string",
        "productEvent": "string",
        "productId": 0,
        "telephone": "string"
      },
      "displayInfoId": 0,
      "modifyDate": "2020-09-11T22:30:34.095Z",
      "productId": 0,
      "reservationDate": "string",
      "reservationEmail": "string",
      "reservationInfoId": 0,
      "reservationName": "string",
      "reservationTelephone": "string",
      "totalPrice": 0
    }
  ],
  "size": 0
}
```
#### Response Header
```
content-type: application/json;charset=UTF-8 
 date: Fri, 11 Sep 2020 20:58:21 GMT 
 transfer-encoding: chunked 

```

### 5.3. Error Message    
| HTTP 코드	| 에러코드 | 에러메세지 |
|:---:|:---:|:---:|
|404 | error-0012 | getBook api result is not exist. |


## 6. inquiry Reservation information

### 6.1. Request
`POST http://{host_url}/api/reservations`
### Parameter
```
{
  "displayInfoId": 0,
  "prices": [
    {
      "count": 0,
      "productPriceId": 0,
      "reservationInfoId": 0,
      "reservationInfoPriceId": 0
    }
  ],
  "productId": 0,
  "reservationEmail": "string",
  "reservationName": "string",
  "reservationTelephone": "string",
  "reservationYearMonthDay": "string"
}
```

### 6.2. Response
#### KEY
#### Response Body
```
{
  "cancelYn": true,
  "createDate": "2020-09-11T23:25:12.715Z",
  "displayInfoId": 0,
  "modifyDate": "2020-09-11T23:25:12.715Z",
  "prices": [
    {
      "count": 0,
      "productPriceId": 0,
      "reservationInfoId": 0,
      "reservationInfoPriceId": 0
    }
  ],
  "productId": 0,
  "reservationDate": "string",
  "reservationEmail": "string",
  "reservationInfoId": 0,
  "reservationName": "string",
  "reservationTelephone": "string"
}
```
#### Response Header
```
content-type: application/json;charset=UTF-8 
 date: Fri, 11 Sep 2020 20:58:21 GMT 
 transfer-encoding: chunked 

```

### 6.3. Error Message    
| HTTP 코드	| 에러코드 | 에러메세지 |
|:---:|:---:|:---:|
|400 | error-0001 | The parameter entered is invalid. at postBook method |

## 7. inquiry Reservation information

### 7.1. Request
`PUT http://{host_url}/api/reservations/{reservationId}`
### Parameter
| Name | Type | Description |
|:---:|:---:|:---:|
|reservationId  | integer | 예약 id |

### 7.2. Response
#### Response Body
```
{
  "cancelYn": true,
  "createDate": "2020-09-11T23:26:37.833Z",
  "displayInfoId": 0,
  "modifyDate": "2020-09-11T23:26:37.833Z",
  "prices": [
    {
      "count": 0,
      "productPriceId": 0,
      "reservationInfoId": 0,
      "reservationInfoPriceId": 0
    }
  ],
  "productId": 0,
  "reservationDate": "string",
  "reservationEmail": "string",
  "reservationInfoId": 0,
  "reservationName": "string",
  "reservationTelephone": "string"
}
```
#### Response Header
```
content-type: application/json;charset=UTF-8 
 date: Fri, 11 Sep 2020 20:58:21 GMT 
 transfer-encoding: chunked 

```

### 7.3. Error Message    
| HTTP 코드	| 에러코드 | 에러메세지 |
|:---:|:---:|:---:|
|400 | error-0010 | The parameter entered is invalid. at cancleBook method |
|400 | error-0011 | cancleBook api result is not exist. |

      
