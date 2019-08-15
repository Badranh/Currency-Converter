# Currency-Converter

## Overview:
Pattern used: MVPVM<br>
Language: JAVA<br>
Network Calls: Retrofit, RXJava, Gson ( implemented Exponential Backoff Strategy)<br>
Dependency Injections: Dagger2<br>
View Bindings: ButterKnife, Android DataBinding ( Views Are Added Dynamically)<br>
Model Code Generators: Lombok<br>
### Benchmarking:
Memory Leaks Detection: LeakCanary(No leaks were found)<br>
Memory was so stable even when updating the currencies values<br>
### Approach:
MVPVM Approach with generic base classes to eliminate redundancy<br>
Generic RecyclerView Adapter class were used<br>
Fragments and Activities extends from dagger <br>
DiffUtil was considered but it wasn't so useful in our case<br>
AppConsts java file that contains App Constants such as : link,Initial starting amount,Initial Currency,API refresh rate<br>
Custom Scopes were used ( Fragments and their dependcies are locally scoped )<br>
Some dependencies were App Scoped<br>


