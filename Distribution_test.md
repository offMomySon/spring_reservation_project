

### RR  DB 512 MM 4096 CON 200
| TS API | 평균요청 처리시간 | 초당 처리 요청수  |   성공요청   |   실패 요청  | 
|--      |--               |--                 |--           |--           |
| categories_api_test | 49.2ms     |  1866.651603/s |   56206      |         0  |
|   products_api_test    | 190.26ms    | 447.16107/s |   13514      |         0  |
|products_with_displayInfoId_api_test| 2.17s	    |   21.394776/s  |   736      |         0  |
|promotion_api_test| 1.01s      | 97.617397/s  |   2987      |         0  |
|rsv_GET_api| 468.09ms    | 197.944963/s |   5991      |         0  |
|rsv_PUT_api| 787.1ms   |  124.89012/s |   3851      |         0  |
|rsv_POST_api | 165.86ms    |  593.460552/s   |   17905      |         0  |



### IP HASH
| TS API | 평균요청 처리시간 | 초당 처리 요청수  |   성공요청   |   실패 요청  | 
|--      |--               |--                 |--           |--           |
| categories_api_test | 51.6ms     |  1861.19304/s |   56022      |         0  |
|   products_api_test   | 379.89ms    | 232.682314/s |   7112      |         0  |
|products_with_displayInfoId_api_test| 2.55s	    |   18.78911/s  |   610      |         0  |
|promotion_api_test| 1.04s     |  95.063416/s  |   2899      |         0  |
|rsv_GET_api| 692.65ms  | 142.805249/s |   4346      |         0  |
|rsv_PUT_api|430.79ms     |  229.437869/s |   6997      |         0  |
|rsv_POST_api | 154.43ms   |  634.9693/s  |   19152      |         0  |


### Least-connected
| TS API | 평균요청 처리시간 | 초당 처리 요청수  |   성공요청   |   실패 요청  | 
|--      |--               |--                 |--           |--           |
| categories_api_test | 49.94ms     |  1720.981548/s |   51878      |         0  |
|   products_api_test | 197.82ms   |  271.845805/s  |   8549      |         0  |
|products_with_displayInfoId_api_test| 1.67s   |  28.60541/s  |   922      |         0  |
|promotion_api_test| 663ms     | 149.116589/s  |   4544      |         0  |
|rsv_GET_api| 294.5ms    | 336.283043/s |   10155      |         0  |
|rsv_PUT_api| 618.66ms |  159.632127/s |   4887      |         0  |
|rsv_POST_api | 71.35ms   | 1345.028836/s   |   40563      |         0  |
