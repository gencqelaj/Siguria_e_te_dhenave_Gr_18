# Siguria_e_te_dhenave_Gr_18

Koduesi i Mors-it-- Kemi dy array-s, njeren me shkronjat e alfabetit dhe tjetren me simbolet perkatese ne kodin mors.
Kemi marre nje variabel te tipit String ne te cilen e ruajme tekstin qe e kemi marrur nga user-i. 
Konstruktor me parameter String sentence e kthen fjaline e koduar ne mors. 
Funksioni ChangeCode e kthen ne Char Array tekstin e marre nga useri dhe e ndajme fjale per fjale. Fjalet prap i ndajme shkronje
per shkronje ku shkronjave perkatese i japim shkrojat korresponduese ne mors.
Funksioni OutputMorse-e kthen fjaline e dhene ne kodin perkates.

Dekoduesi i Mors-it -- Edhe ketu kemi dy arrays, njeren me shkronjat e mors-it dhe tjetra me shkronjat korresponduese ne 
alfabetin normal. 
E ndajme tekstin serish fjale per fjale, fjalet i ndajme shkronje per shkronje me ane te funksioneve NdarjaEFjaleve dhe
NdarjaEShkronjave. 
Konstruktori e kthen tekstin e dekoduar.

////////////////////////////////////////////


KOMANDA PERMUTATION; 
-Komanda permutation është komanda e cila transformon plaintextin në nivel të blloqeve duke i zhvendosur karakteret sipas një permutacioni. 
-Se pari krijojme objektin e klases permutation 
-Në bazë te konstruktorit ne vendosim celesin si parameter të parë dhe paintext si parametër të dytë.
-Pasi dy nënkomandat(encrypt dhe decrypt) përdorin celesin dhe plaintext-in , keto janë inicializuar tek konstruktori në menyrë që të reduktohet kodi.
Gjithashtu në konstruktor janë bërë disa manipulime me stringje dhe arrays të cilat nevojiten tek dy komandat encrypt apo decypt.
-Parametri i parë i konstruktorit është celësi, ndërsa i dyti plaintexti.Këto dy parametra inicializohen permes args[2] dhe args[3].Në rast se plaintexti nuk i mbush plotësisht blloqet atëherë plaintexti mbushet me karakterin "ë" derisa blloqet të mbushen plotësisht.Në rast se plaintexti përmban hapësira, ato eliminohen.
-Permes args[1] ne vendosim që textin ta enkriptojmë apo dekriptojmë.
-Pas inicializimit te args[1] thirren funksionet encrypt() ose decrypt() ne bazë të zgjedhjes.

-Nënkomanda encrypt :
	-Plaintexti i dhënë ndahet në blloqe me gjatësi në varësi të 	qelësit.Ne konstruktor është perdorur funksioni splitToChar që ndanë në një array 	secilin bllok të tekstit. 
	-Cdo bllok i tekstit do ta ketë celësin e tij kur ai të shfaqet vizualisht.
	-Celesi ne konstruktor fillimisht shnderrohet ne int , pastaj në një array për secilin numër. 
	-Me anë të dy for loopave ne kemi qasje tek cdo karakter i secilit bllok .
	-Me anë të një StringBuilder ne mund që ta ndërtojmë një string të ri i bazuar në plaintext , por duke marrë karakteret në bazë te celesit, 		kryesisht duke perdorur funksionet sb.append(array.charAt[i]).
	-Një StringBuilder mbushet me karaktere sa gjatësia e një celesi(blloku) dhe pastaj shfaqet në output . Kjo procedurë vazhdon deri sa të perfundohen 	të gjitha blloqet .
	-Për cdo StringBuilder qe formohet, shtohet në një array e cila më pastaj shfaqet në output dhe na tregon komplet tekstin e enkriptuar.Kjo procedurë 	është perdorur kryesisht vetëm per ta marrë më lehtë (copy) tekstin e enkriptuar , në rast se duam ta  testojmë edhe nënkomandën decrypt.  

-Nënkomanda decrypt :
	-Funksioni decrypt ka përafërsisht procedurë të njëjtë me atë encrypt vetëm se në bazë të formulës se komandës Permutation , tani në bazë të celësit 	dekriptohet teksti.
	-Për cdo bllok të tekstit rradhiten karakteret në bazë të celësit të dhenë dhe pastaj shfaqen në output. 

-Ky ishte përshkrimi i shkurtë sa i përket Komandës Permutation.


////////////////////////////////////////////


Tap Code
Tap code eshte nje enkriptim i thjeshte i tekstit i perdorur nga ushtaret ne burgje. Fillimisht krijohen 2 matrica, njera e cila i perfshin shkronjat e alfabetit anglez, perveq shkronjes K e cila zevendesohet me C dhe tjetra e perbere nga kombinime te ndryshme te pikave, te cilat zevendesojne "taps" qe nevojiten per te formuar nje shkronje ne baze te rreshtit dhe kolones se ku gjendet ne matricen e pare ajo shkronje.

Pastaj eshte krijuar nje String me emrin input si variabel private i cili eshte argumenti i 3t i Tap-code, dhe qe eshte dhene ne konstruktor ne menyre qe te inicializohet, arsyja pse eshte ne konstruktor eshte sepse eshte variabel private dhe nuk mund t'i qasesh nga klasa tjera.

Pastaj funksioni indexof kontrollon nese shkronjat e dhena te tekstit gjenden ne matricen e pare, si dhe shton simbolet perkatese ne String-un stringu dhe ne fund e kthen kete variabel.

Funksioni enkripto e ben enkriptimin e tekstit, brenda tij fillimisht thirret funksioni isValidEnkripto per te validuar inputin, pastaj krijohet nje array e cila e ndan teksin ne karaktere dhe secili karakter eshte element i ketij array. Pastaj shikohet nese si karakter kemi shkronjen K dhe e zevendesojme me simbolin e shkronjes C(kusht i detyres) ndersa shkronjat tjera i zevendesojme me simbolet perkatese. Dhe ne fund shfaqet ne ekran teksti i enkriptuar.

Funksioni dekripto ben dekriptimin e tekstit te enkriptuar paraprakishte, fillimisht thirret funksioni isValidDekripto per te shikuar nese teksti i dhene permban vetem hapesira dhe shkronja, pastaj behet ndarja e tekstit ne fjale per qdo 2 hapesira (pra aty ku jane te shenuara qdo 2 hapesira behet ndarja me split) dhe qdo fjale behet pjese e nje array, pastaj qdo fjale ndahet ne qdo te dyten hapesire (sepse nje hapesire rezervohet per ndarjen e pikave sipas rreshtit dhe kolones) dhe keto futen ne nje array tjeter, ku qdo karakter i qdo fjale eshte si element ne vete i ketij array. Ne fund behet dekriptimi i ketyre karakterve dhe shtohen ne variablen plainString dhe pastaj shfaqet ne ekran teksti i dekriptuar.

Funksioni isValidEnkripto e kthen true nese teksti permban vetem hapesira dhe shkronja te medha prej A deri ne Z.

Funksioni isValidDekripto e kthen true nese teksti permban vetem pika dhe hapesira.

Ky ishte nje pershkrim i thjeshte sa i perket komandes Tap-code dhe nenkomandav Enkripto dhe Dekripto.
