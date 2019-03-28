# spring-examples

- 빌드 : ./gradlew :{모듈명}:build  (예:./gradlew :webflux-demo:build)

## Webflux VS MVC 성능비교 (Non Blocking VS Blocking)

* 컴퓨팅 : AWS t2.large (CPU: 2, Memory: 8G)
* 저장소 : Aurora (Mysql 5.7), Elastic Cache (Redis 5.0.3)
* 성능 테스트 툴 : Jmeter (Thread : 500 * Count : 500)

#### Webflux + Non Blocking Redis Read

| Samples | Average | Min | Max | Error % | Throughput | CPU(Max) |
| :-------| :------ | :--- | :--- | :---- | :--------- | :--------- |
| 250,000 | 150 | 2 | 1305 | 0.00% | 3118.1/sec | 97% |
| 250,000 | 149 | 2 | 1440 | 0.00% | 3134/sec | 94% |
| 250,000 | 149 | 2 | 1174 | 0.00% | 3153.1/sec | 98% |

#### Webflux + Non Blocking Redis Write

| Samples | Average | Min | Max | Error % | Throughput | CPU(Max) |
| :-------| :------ | :--- | :--- | :---- | :--------- | :--------- |
| 250,000 | 162 | 4 | 12536 | 4.13% | 2867.1/sec | 97% |
| 250,000 | 159 | 2 | 11476 | 3.74% | 2925/sec | 94% |
| 250,000 | 162 | 4 | 14308 | 4.01% | 2814.1/sec | 98% |

#### MVC + Blocking Redis Read

| Samples | Average | Min | Max | Error % | Throughput | CPU(Max) |
| :-------| :------ | :--- | :--- | :---- | :--------- | :--------- |
| 250,000 | 328 | 3 | 1506 | 0.00% | 1477.2/sec | 98% |
| 250,000 | 327 | 3 | 1561 | 0.00% | 1481.0/sec | 97% |
| 250,000 | 327 | 3 | 1419 | 0.00% | 1480.0/sec | 98% |

#### Webflux + Non Blocking Mysql Read (CompletableFuture)

| Samples | Average | Min | Max | Error % | Throughput | CPU(Max) |
| :-------| :------ | :--- | :--- | :---- | :--------- | :--------- |
| 250,000 | 291 | 4 | 2335 | 0.00% | 1648.2/sec | 98% |
| 250,000 | 291 | 4 | 2087 | 0.00% | 1650.0/sec | 97% |
| 250,000 | 292 | 2 | 2034 | 0.00% | 1644.7/sec | 95% |

#### MVC + Blocking Mysql Read

| Samples | Average | Min | Max | Error % | Throughput | CPU(Max) |
| :-------| :------ | :--- | :--- | :---- | :--------- | :--------- |
| 250,000 | 330 | 4 | 4208 | 0.00% | 1456.2/sec | 52% |
| 250,000 | 339 | 2 | 5523 | 0.00% | 1411.0/sec | 50% |
| 250,000 | 337 | 4 | 4285 | 0.00% | 1425.0/sec | 52% |


#### 결론
- Blocking 으로 만들면 컴퓨팅 자원을 비효율적으로 사용하기 때문에 AWS 사장의 배를 더 배부르게 한다.(돈을 더 번다)
- Non Blocking 을 사용해서 리소스를 효율적으로 사용하자!! 

