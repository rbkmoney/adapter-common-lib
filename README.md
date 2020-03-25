# Adapter-common-lib

Библиотека, которая включает общие для всех адаптеров классы.

#####Маскирование номера карты на уровне logback:

Для подкючения маскирования логов в wetkitty в конфиге небходимо прописать шаблон [для адаптеров](https://github.com/rbkmoney/wetkitty/blob/master/sls/wetkitty/macroservice/service/files/service-adapter-java.sls.tpl): [Пример PR](https://github.com/rbkmoney/wetkitty/pull/3187/files#diff-d12f91e6a7df39dadf9dfc029137bf06R2)

Если необходимо вынести какие-то параметры в контекст логов, то используется [декоратор](https://github.com/rbkmoney/adapter-common-lib/blob/master/src/main/java/com/rbkmoney/adapter/common/handler/ServerHandlerMdcDecorator.java)
Параметры в контекст будут добавлены из TransactionInfo().extra те, значения которых попадут под маску карты (12-19 цифр). 
Если добавление всех параметров из extra является избыточным, то необходимо создать свой декторатор, указав только необходимые для передачи поля: [Пример](https://github.com/rbkmoney/adapter-rsb/blob/master/src/main/java/com/rbkmoney/adapter/rsb/handler/RsbServerHandlerMdcDecorator.java)