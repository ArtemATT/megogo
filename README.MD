# Test Task Description
1. Існує ендпоінт, що має повертати поточний час.
https://epg.megogo.net/time
Зробити тест, що сервер дійсно повертає поточний час.


2. Існує ендпоінт, що має повертати розклад телепередач на наступні 24 години.
https://epg.megogo.net/channel?video_ids=
Для наступних video_id 1639111, 1585681, 1639231 перевірити, що:
а) програми телепередач (поле programs) відсортовані за полем start_timestamp;
б) на поточний час існує програма в розкладі (поточний timestamp в рамках start_timestamp та end_timestamp);
в) в розкладі немає телепередач з минулого та далі ніж на 24 години вперед.

### Регламент:
- завдання повинно бути виконане у вигляді автоматизованих тестів
- мова програмування java або kotlin
- система збірки maven або gradle
- бібліотека для тестування junit або testng

### Додатково як бонус (за можливості):
- інтегрувати будь-який report tool
- багатопоточне виконання тестів
- запакувати в docker контейнер