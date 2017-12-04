# mnogopotochka
Запуск в idea:
  Необходимо добавить в проект библиотеки (папку libs) MPJ.
  http://mpj-express.org/ - скачать тут.
  https://habrahabr.ru/post/105021/ - статья на хабре.
  https://habrahabr.ru/post/105408/ - более подробный пример.
Конфигурация:
  -Main class: runtime.starter.MPJRun
  -VM options: -jar $MPJ_HOME/lib/starter.jar com.mikheev.mnogopotochka.lab2.MonteCarloPi -np 4 
  -Environment variables: MPJ_HOME=/home/kron/projects/mpj/mpj-v0_44/
  
P.S: В VM options мне пришлось прописывать полный путь до starter.jar, возможно где-то ошибся.
