# WebProject

Egy webalkalmazés, amin keresztül egy nagyobb rendezvény megszervezését tudjuk lebonyolítani.

Funkcionalitások: 
	Bejelentkezés: 
				- adminként 
				- szervezoként (csak az admin által megadott felhasználók léphetnek be, regisztrálásra nincs lehetoség) 
	Admin: 
			- szervezok adatainak bevitele/módosítása/törlése 
			- új rendezvény megnevezése 
			- kiválasztott rendezvényhez részfeladat hozzáadása (részfeladat neve, határido, ameddig meg kell oldani), illetve annak kijelölése, hogy
			ki felel a részfeladat megoldásáért 
		   - személyes üzenet küldése egy bizonyos szervezonek 
		   - összesítés egy kiválasztott rendezvény esetén: megoldatlan/megoldott részfeladatok, túllépett határidok (pl. különbözo színnel való érzékeltetése) / kérésre egy bizonyos részfeladattal kapcsolatos részletek megjelentetése vagy eltuntetése
	Szervezo: 
			- az admintól kapott üzenetek listázása/törlése 
			- a rábízott (megoldott/megoldatlan) részfeladatok listázása 
			- megoldatlan részfeladatok állapotának módosítása (újabb jelentés hozzáadása az aktuális dátummal, illetve lezárása, szintén az aktuális dátummal) 
			- azon rendezvények esetén, amelyekben o is szerepet kapott, megtekintheti, hogy a többiek hogy állnak az egyes részfeladatokkal (csak ezek állapotát láthatja, a részletek -jelentések- nélkül)