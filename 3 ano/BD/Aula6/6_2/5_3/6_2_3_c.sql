--a)
SELECT Prescricao.Paciente.numUtente, Prescricao.Paciente.nome FROM Prescricao.Paciente LEFT OUTER JOIN Prescricao.Prescricao ON Prescricao.Paciente.numUtente=Prescricao.Prescricao.numUtente WHERE Prescricao.Prescricao.numUtente IS NULL;

--b)
SELECT especialidade, COUNT(numPresc) AS total_prescricoes FROM Prescricao.prescricao JOIN Prescricao.medico ON numMedico=numSNS GROUP BY especialidade;

---c)
SELECT nome, COUNT(numPresc) AS total_prescricoes FROM Prescricao.prescricao JOIN Prescricao.Farmacia ON farmacia=nome GROUP BY nome;

--d)
SELECT Prescricao.farmaco.nome FROM Prescricao.farmaceutica,Prescricao.farmaco WHERE Prescricao.Farmaceutica.numReg=906 EXCEPT 
(SELECT nomeFarmaco FROM Prescricao.Presc_farmaco WHERE numRegFarm=906);

--e)
SELECT farmacia, nome, qtd_farmacos FROM Prescricao.Farmaceutica JOIN 
        (SELECT farmacia, numRegFarm, COUNT(numRegFarm) AS qtd_farmacos FROM (SELECT farmacia, numRegFarm FROM Prescricao.Presc_farmaco
                JOIN Prescricao.Prescricao ON Presc_farmaco.numPresc=Prescricao.numPresc
                WHERE farmacia IS NOT NULL) AS FARM_SELLED
            GROUP BY farmacia, numRegFarm
            ) AS AUX
ON numRegFarm=numReg;

--f)
SELECT nome FROM Prescricao.Paciente JOIN 
        (SELECT numUtente, COUNT(numMedico) AS medicos_dif FROM ( SELECT numUtente, numMedico FROM Prescricao.Prescricao ) AS AUX
            GROUP BY numUtente
            HAVING (COUNT(numMedico)>1 )) AS MED
ON Paciente.numUtente=MED.numUtente;
