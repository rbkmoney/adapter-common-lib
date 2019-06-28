# Adapter-common-lib

Библиотека, которая включает общие для всех адаптеров классы.


### Настройки

Добавить в `pom.xml` в зависимости

```
<dependency>
    <groupId>com.rbkmoney</groupId>
    <artifactId>adapter-common-lib</artifactId>
    <version>0.0.7-SNAPSHOT</version>
</dependency>
```

### Настройки

Общее взаимодействие с провайдерами и mpi

При взаимодействии с MPI после запроса на вовлеченность карты в 3ds получаем 4 параметра:


Название | Описание | Пример
------------ | ------------- | -------------
**acsUrl** | URL Access Control Server | https://ds1.mirconnect.ru:443/miraccept/pareq
**MD** | Merchant Data | 1BC462SsiX2
**PaReq** | - | eJxVUdtugzAM...2/MPI+Ki5w==
**TermUrl** | URL for result ACS | https://wrapper.rbk.money/adapter/chacha

В случае с адаптерами в TermUrl надо также передать шаблон `{?termination_uri}`, пример:

```
https://wrapper.rbk.money/adapter/chacha/term_url{?termination_uri}
```

где `termination_uri` это url который подменяется на стороне checkout-а для дальнейшего редиректа покупателя на страницу магазина.

Если по какой-то причине данный адрес не известен, то можно воспользоваться адресом по умолчанию:

```
https://checkout.rbk.money/v1/finish-interaction.html
```

Либо же использовать вместо `TermUrl`, если мы не получаем данные от провайдера, т.к. все происходит на его стороне и мы можем только поллить статус
