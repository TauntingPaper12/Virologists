# ProjLab

|Hét: |2    |3    |4    |5    |6    |7    |8    |9/10 |11   |12/13|14   |
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|Pont:|9.5  |19   |29   |18   |19   |19.5 |43   |33   |15   |40   |28   |

Használt programok:

- IntelliJ IDEA
- Microsoft Word
- StarUML
- LaTex

Feladatleírás:
>Egy pusztító biológiai katasztrófában mindenki elvesztette a látását. A városban virológusok kóborolnak és gyógymódot kutatnak.
A különféle vírusok genetikai kódja egy-egy laboratórium falára van felkarcolva. Ahhoz, hogy egy virológus a genetikai kódot megismerje, el kell jutnia az adott laboratóriumba, és le kell tapogatnia a genetikai kódot. Ez alapján lehet majd vagy vakcinát, vagy magát a vírust előállítani.

>Egy már megismert kód alapján a vírus vagy a vakcina (közös nevükön: ágens) létrehozható, de ehhez a virológusnak a szükséges mennyiségű aminosavval és nukleotiddal (közös néven: anyag) kell rendelkeznie. Az aminosavak és a nukleotidok különféle raktárakban szedhetők össze, de mindenki csak egy korlátos mennyiséget hordhat belőlük magánál. Ha a begyűjtött anyag mennyisége eléri ezt a korlátot, akkor többet már nem tud magához venni.

>Egy virológus az előállított ágenst rövid időn belül felhasználhatja: vagy saját magára, vagy egy másik virológusra kenheti, de csak akkor, ha a kenést végző virológus meg tudja érinteni a másikat. A felkent ágensek csak adott ideig hatásosak, az idő letelte után elbomlanak, hatásuk megszűnik.

>Sokféle ágens létezik. Van olyan, amelyik vitustáncot okoz: az áldozat kontrollálatlanul, véletlenszerű mozgással kezd el haladni. Van olyan, amely megvéd attól, hogy más virológusok egyes ágensei hatással legyenek az ágens hatása alatt álló virológusra. Van olyan ágens, amely megbénít, így amíg az ágens hatása tart, az áldozat nem tud semmit csinálni (lebénul). Van amelyiktől az áldozat elfelejti a már megismert genetikai kódokat.

>A virológusok a vándorlás során védőfelszereléseket is gyűjthetnek. A védőfelszerelések a városban vannak szétszórva. Egy felszerelés megszerzéséhez a virológusnak a megfelelő óvóhelyre kell bemennie, és a védőfelszerelést fel kell vennie. A felszerelések csak azt a virológust védik, aki viseli őket. A felszerelések hatása addig tart, amíg a virológus viseli őket. Egyszerre azonban maximum 3 felszerelés viselhető.

>Sokféle védőfelszerelés létezik. Van védőköpeny, amely az ágenseket 82,3%-os hatásfokkal tartja távol. Van zsák, amely megnöveli a virológus anyaggyűjtő képességét. Van kesztyű, amellyel a felkent ágens a kenőre visszadobható.

>A virológusok a vándorlásuk során találkozhatnak egymással. Találkozáskor elmehetnek egymás mellett, ágenst kenhetnek a másik virológusra, vagy, amíg a másik virológus lebénult állapotban van, elvehetik a másik anyagkészletét és felszerelését.

>A játékot az a virológus nyeri, aki legelőször megtanulja az összes fellelhető genetikai kódot. A játéktér eltérő oldalszámú sokszögekből álló rácsot alkot, a virológusok ennek mezőin (szabad terület, raktár, óvóhely, laboratórium stb.) lépkedhetnek.

> Változás (7. hét)
> A kesztyűk hatása 3 használat után megszűnik, a kesztyűk lehámlanak, eltűnnek.
> Egyes laborokban automatikusan meg lehet fertőződni a medvevírussal (hacsak nincs rajtunk védőágens vagy köpeny), amitől a fertőzőtt medvetáncot kezd járni
> - a medvetáncos (röviden: medve) véletlenszerű mozgással közlekedik
> - a medve minden útjába eső raktárban elpusztítja az ott lévő anyagokat
> - a medve minden útjába eső virológust megken a medvevírussal (kifogyhatatlan a készlete), ami ellen a szokásos védelmek működnek
> 
> A medve egy módon "gyógyítható": az óvóhelyeken található balta nevű eszköz is. Bárki, akit ezzel megcsapnak, meghal, de nem fertőz tovább. A balta egy használat után kicsorbul és nem működik tovább, de amíg nem dobják el, foglalja a helyet.
