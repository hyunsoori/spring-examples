# spring-examples

## 성능 비교 (Reactive VS Blocking)
#### Reactive Redis
| Samples | Average | Min | Max | Error % | Throughput |
| 10,000 | 429 | 6 | 1005 | 0.00% | 958.2/sec |
| 10,000 | 449 | 5 | 965 | 0.00% | 1117.1.2/sec |
| 10,000 | 237 | 4 | 544 | 0.00% | 1279.1/sec |

#### Reactive Mysql (CompletableFuture)
| Samples | Average | Min | Max | Error % | Throughput |
| 10,000 | 1194 | 9 | 2411 | 0.00% | 568.7/sec |
| 10,000 | 1124 | 10 | 2144 | 0.00% | 589.4/sec |
| 10,000 | 1177 | 9 | 2898 | 0.00% | 563.1/sec |

#### Reactive Redis
| Samples | Average | Min | Max | Error % | Throughput |
| 10,000 | 429 | 6 | 1005 | 0.00% | 1058.2/sec |
| 10,000 | 449 | 5 | 965 | 0.00% | 1117.1.2/sec |
| 10,000 | 237 | 4 | 544 | 0.00% | 1279.1/sec |

#### Reactive Mysql (CompletableFuture)
| Samples | Average | Min | Max | Error % | Throughput |
| 10,000 | 1194 | 9 | 2411 | 0.00% | 568.7/sec |
| 10,000 | 1124 | 10 | 2144 | 0.00% | 589.4/sec |
| 10,000 | 1177 | 9 | 2898 | 0.00% | 563.1/sec |


#### Blocking Redis
| Samples | Average | Min | Max | Error % | Throughput |
| 10,000 | 437 | 3 | 1790 | 0.00% | 1005.2/sec |
| 10,000 | 364 | 2 | 774 | 0.00% | 1137.1.2/sec |
| 10,000 | 375 | 3 | 782 | 0.00% | 1113.8/sec |

#### Blocking Mysql
| Samples | Average | Min | Max | Error % | Throughput |
| 10,000 | 1303 | 7 | 2723 | 0.00% | 529.5/sec |
| 10,000 | 1266 | 8 | 2913 | 0.00% | 532.9/sec |
| 10,000 | 1320 | 8 | 2623 | 0.00% | 526.1/sec |

