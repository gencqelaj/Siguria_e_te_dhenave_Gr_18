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

//
Faza 2

Fillimisht kemi ruajtur ne nje string path-in se ku do te ruhen qelsat.

Hapi tjeter ka qene marrja e stringut qe kemi dhene dhe kthimi i tij ne nje dokument(file) duke perdorur newDocumentBuilder().
Pas kesaj kemi parsuar variablen ne te cilen eshte ruajtur newDocumentBuilder()-i me ane te nje stringbuilder dhe e kemi ruajtur ne nje variabel te re doc.

Funksioni  writeXmlDocumentToXmlFile merr si parametra nje xmlDocument dhe nje String fileName. Me ane te nje try and catch rregullojme validimet. Ne try vendosim 


Funksioni i rradhes private static Document ParseXMLFile ka si parameter nje String file. Me ane te operatorit new krijojme nje dokument te ri. Pas kesaj nga dokumenti i ri krijojme nje instance te re me ane te newInstance().
E krijojme nje dokument te ri ne variablen dBuilder me ane te newDocumentBuilder. Ne fund e parsojme dBuilder e cila merr si parameter inputFile-n dmth dokumentin qe ne e japin ne input dhe e ben return doc.

Create-user

Fillimisht krijojm nje objekt te ri nga KeyPairGenerator dhe perdorim metoden getInstance e cila ka si parameter RSA-ne. Pastaj serish krijojme nje instance te re, kesaj rradhe nga KeyPair e cila na gjeneron qiftin e qelsave me ane te metodes genKeyPair().
Qelsin privat e vendosim ne variablen privKey kurse qelsin publik ne pubKey.

Ne variablen n e vendosim Modulusin e qelsit private te cilit i qasemi me ane te metodes getModulus(). Ne variablen "e" e vendosim eksponentin e qelsit publik me ane te metodes getPublicExponent. Ne variablen "d" e vendosim eksponentin e qelsit privat me ane te metodes getPrivateExponent. Ne variablen p e vendosim nje vlere te nje numri primar te cilit i qasemi me ane te metodes getPrimeP(). Te njejten gje e bejme edhe ne variablen q me ane te metodes getPrimeQ(). Ne variablen dp vendosim vleren te cilen e marrim me ane te metodes getPrimeExponentP() kurse ne variablen dq vendosim vleren te cilen e marrim me ane te metodes getPrimeExponentQ(). Krejt ne fund ne variablen inverseQ vendosim vleren te cilen e marrim permes metodes getCrtCoefficient().

Me ane te nje stringbuilder-i krijojme nje StringBuilder te ri. I bejme append me RSAKeyValue si dhe gjithashtu perdorim write-n per te shfaqur Modulusin ne variablen n, Eksponentin ne variablen e, dy numrat prime ne variablat p dhe q, DP dhe DQ e shfaqim ne dp dhe dq e krejtesisht ne fund shfaqen edhe inverseQ dhe d. Ne fund mbyllet builder.append serish me RSAKeyValue.

Ne variablen modulus vendosim vleren te cilen e marrim me ane te metodes pubKey.getModulus(). Ne publicExponent vendosim privKey.getPublicExponent() te cilen e marrim dmth nga qelsi privat. I bejme append me RSAKeyValue. I bejme write Modulusin te cilin e kemi ruajtur ne variablen "modulus" dhe Exponentin te cilin e kemi ruajtur ne variablen "publicExponent" . Ne fund serish i bejme append me nje RSAKeyValue.

Variablen e mehershme doc tani e kthejme ne nje dokument(file) me ane te ConvertStringToDocumentBuilder e cila si parameter ka builder.toString() . Ne PublicKeyDoc e ruajme nje dokument(file) tjeter te cilen serish e krijojme me ane te funksionit ConvertStringToDocumentBuilder qe si parameter kesaj here ka Publicbuilder.toString().

Ne fund na shfaqen qelesat publik dhe privat te cileve ua bashkangjisim ".xml" perkatesisht ".pub.xml" dhe ne kete menyre i ruajme si XML File.

Pasi kemi perfunduar me kete funksion vazhdojme me funksionin GetRequest i cili merr nje url nga interneti.
Fillmisht ne variablen urlObj e ruajm url-ne e marre nga inputi. Vendosim nje connection me ane te (HttpURLConnection) urlObj.openConnection() dhe gjithashtu me poshte specifikojme qe jemi duke perdorur GET metoden.

Ne variablen responseCode ruajme pergjigjen e marrur nga konektimi. Krijojme nje StringBuffer me emerimin response.
Pas kesaj variablen responseCode e kemi futur ne nje if-statement duke e shikuar se a perputhet me HttpURLConnection. Nqs perputhet atehere krijohet nje BufferReader i ri me emerimin inputreader i cili ka si parameter nje new InputStreamReader, e kjo e fundit ka si parameter connection.getInputStream(). Krijojme nje variabel inputLine te tipit String. Fusim ne perdorim nje while-loop duke shikuar ne te ese inputLine eshte e njejte me inputreader.readLine()) dhe keto dy te fundit nuk jane null. Nqs nuk jane null atehere do te bejme append ne variablen "response". Ne fund kemi return response.toString() e cila na e kthen si string.

Funksioni i rradhes generatePublic ka si parameter nje String file, nje String path, nje String user si dhe nje vlere bool-eane "isFile". Ne kete funksion fusim ne perdorim nje if-statement e cila ka si parameter isFile-n. Pra, nese vlera bool-eane eshte true atehere krijojme nje dokument te ri ne variablen doc vlera e se ciles eshte ParseXMLFile me parametrin file. Pas krijimit te dokumentit ateher e perdorim metoden .normalize().
Ne variablat Modulus dhe Experiment marrim "item"-at e pare te dokumentit. Krijojme nje StringBuilder te ri me emertimin Publicbuilder. Publicbuilder e bejme append me RSAKeyValue. Kesaj ia bashkangjesim edhe Modulus-in dhe Exponent-in. Ne fund e bejme append serish me nje RSAKeyValue. Variablen PublicKeyDoc me ane te funksionit ConvertStringToDocumentBuilder e kthejme ne nje file.
Nqs nuk plotesohet kushti ateher variabla PublicKeyDoc menjeher kthehet ne nje dokument(file) me ane te funksionit ConvertStringToDocumentBuilder. Kjo kthen si Xml Document, PublicKeyDoc si dhe path-in dhe user-in.

Delete-user

Funksioni delete user merr dy parametra: nje String user dhe nje String path.  Krijojme dy vlera bool-eane existPrivate dhe existPublic ku keto variabla shohin me ane te metodes FileExist se a ekzistojne file-t me prapashtesat ".xml" perkatesisht ".pub.xml".

Perdorim nje if-statement.
Nqs plotesohet kushti qe ekziston qelesi privat ateher krijojme nje file te ri me prapashtesen ".xml". E fshijme ate file dhe tregojme se ky file eshte fshire nga "keys" qe ne kete rast eshte path-i.
Nqs plotesohet kushti qe ekziston qelesi publik ateher krijojme nje file te ri me prapashtesen ".pub.xml". E fshijme ate file dhe tregojme se ky file eshte fshire nga "keys" qe ne kete rast eshte path-i.
Por nqs nuk ekzistojne asnjera ateher do te shfaqet se qelesi  me kete emertim nuk ekziston.

Importi

Fillimisht ne kete funksion krijojme variablen bool-eane getRequest e cila merr nga interneti nje http apo https te caktuar. Gjithashtu definojme nje variabel tjeter bool-eane me emertimin isPrivate key.

Nqs variabla bool-eane getRequest ekziston ateher ne na gjenerohet menjeher qelesi publik.
Perndryshe, shohim fillimisht se path-i i dhene a e permban ".xml". Nqs e permban ateher e perdorim funksionin isPrivateKey me parameter sourceFile. Nqs ekziston sourceFile ateher krijojme nje file te ri me ate emertim dhe ne te vendosim vlerat e qelesave publik dhe privat te cilat i kemi ruajtur ne variablat myObj dhe myObj1. Importimi ndodh pasi qe perdorim funksionin export(sourceFile,user).
Nqs nuk ekziston qelesi privat ateher ne shfaqim vetem qelesin publik dhe tregojme qe kemi shfaqur vetem qelesin publik e jo edhe ate privat. Perndryshe do te na shfaqet qe fajlli i dhene nuk eshte qeles valid.


Funksioni isPrivateKey merr si parameter nje String file. Krijon nje dokument doc te cilit i bashkangjitet vlera ParseXMLFile qe ka si parameter file-n e dhene ne input. Shohim me ane te metodes  getElementsByTagName("P") nese permbahet tagu me P. Nqs permbahet ateher kthehet vlera True, e nqs nuk permbahet ky tag kthehet vlera False. 

Funksioni Export_xml i cili ka si parameter String sourceFile dhe String destFile kthen nje string i cili na tregon se ku po ruhet eksportohet fajlli. Me ane te metodes "move" i eksportojme file-t.
    

Eksporti

Ky funksion merr si parameter dy file, file1 dhe file2. Iniciojme nje  FileInputStream dhe FileOutputStream me vlerat null.
Krijojme nje File te ri me emrin "infile" ne te cilin ruajm path-in e file1. Kurse ne variablen outfile krijojme nje file te ri e cila si parameter e ka file2. Ne instream dhe outstream krijojme me ane te operatorit new, nje FileInputStream qe ka si parameter infile-n dhe nje FileOutputStream qe ka si parameter nje outfile.
E marrim nje array buffer  e cila i kthen ne bytes. Iniciojme nje variabel length te tipit int. 
Fusim ne perdorim while-loop  e cila e sheh nese length eshte e njejte me instream.read(buffer) ku keto te dyja duhet te jene me te medha se 0. Nqs plotesohet ky kusht ateher na shfaqet nje outstream.write qe ka si parametra buffer-in, 0 dhe length-in.
Ne fund vetem i mbyllim me ane te .close()

Funksioni  exportAndPrint qe ka si parameter nje String path, e ruan path-in ne path1. Pas kesaj barazojme nje String content me nje Files.readString e cila ka si parameter Paths.get ku kjo e fundit merr si parameter te vetin path1-shin. Ne fund kthehet content-i. Krejtesisht ne fund kthehet path1.

Funskioni exportToFile qe ka si parameter dy stringje: path1 dhe path2 krijon nje FileInputStream dhe nje FileOutputStream ku i inicion me vleren null. Ky funksion e krijn nje file te ri ne variablen infile ku ruan vleren e path1 si dhe krijon nje file te ri ne variablen outfile me parameter path2. Edhe ketu instream dhe outstream barazohen me vlerat FileInputStream qe ka si parameter infile, perkatesisht me vleren e FileOutputStream qe ka si parameter outfile. Shohim serish me ane te nje while-loop nese length-i i deklaruar dhe array buffer jane me te medha se 0. Nqs jane ateher do te na shfaqet outstream.write qe ka si parametra buffer, 0, length.
Ne fund gjithashtu perdorim .close per ti mbyllur instream dhe outstream. Nese eshte e suksesshme do te na shfaqet mesazhi.

Funksioni existKey ka si parameter nje String path. Ky funksion kriojn nje file te ri me path-in e dhen ne input. Dhe sheh a eshte e vertet vlera bool. Gjithashtu e ben return bool.

Funksioni FileExists ka si parametra   String user, String path dhe String type. Ne kete funksion krijojme nje file te ri ne variablen tempFile. E verifikojme se a ekziston me ane te variables bool-eane exists. Ne fund e bejme return exist.

Funksioni isValidPath ka si parameter String path. Ky funksion sheh e merr path-in e dhene ne input. I verifikon gabimet dhe ne fund e kthen return true.

Funksioni write  ka si parametra StringBuilder builder, String tag dhe BigInteger bigInt. Ne kete funksion bejme append t-ne, tag-un, encode(bigInt) si dhe e permbyllim me tag.          

Funksioni writeString  ka si parametra StringBuilder builder, String tag, String string.  Ne kete funksion bejme append t-ne, tag-un, string si dhe e permbyllim me tag.           

Funksioni encode  ka si parameter BigInteger b. Ne kete funksion e kthejme ne nje array b1 b-ne se ciles i jemi qasur me ane te metodes toByteArray. Ne String k enkodojme stringun me ane te Base64.getEncoder().encodeToString(b1). Krejtesisht ne fund kemi return k.

    


  


 
