


dic = readcell('u_item.txt');
%clc clear all      
% Código base para deteção de pares similares
udata=load('u.data');% Carrega o ficheiro dos dados dos filmes
% Fica apenas com as duas primeiras colunas

u= udata(1:end,1:2); clear udata;  

% Lista de utilizadores
users = unique(u(:,1));     % Extrai os IDs dos utilizadores
Nu= length(users);          % Número de utilizadores  

% Constrói a lista de filmes para cada utilizador
Set= cell(Nu,1); % Usa células  

for n = 1:Nu  % Para cada utilizador    
    % Obtém os filmes de cada um    
    ind = find(u(:,1) == users(n)); 
    % E guarda num array. Usa células porque utilizador tem um número 
    % diferente de filmes. Se fossem iguais podia ser um array     
    Set{n} = [Set{n} u(ind,2)];
end

%Criação da MinHash para os utilizadores
K = 50;
MinHashValue = inf(Nu,K);
for i = 1:Nu 
    conjunto = Set{i};
    for j = 1:length(conjunto)         
        chave = char(conjunto(j));        
        hash = zeros(1,K);         
        for l = 1:K             
            chave = [chave num2str(l)];            
            hash(l) = DJB31MA(chave, 127);
            
        end
        MinHashValue(i,:) = min([MinHashValue(i,:);hash]);
    end
end
%

%Criação da MinHash para os Filmes
Nm = length(dic);
MinHashMovies=inf(Nm,K);
shinglesLength = 3;
for i = 1 : Nm
    filme = dic{i, 1};
    for j = 1 : length(filme)-shinglesLength+1
        shingle = lower(char(filme(j:j+shinglesLength-1)));
        h=zeros(1,K);
        for n = 1 : K
            shingle = [shingle num2str(n)];
            h(n)=DJB31MA(shingle,127);
        end
        MinHashMovies(i,:)=min([MinHashMovies(i,:);h]);
    end
end
%

save 'ValoresGRD.mat' dic users Nu Set MinHashValue MinHashMovies K

%set para os filmes
% ultimo set para o minash
% k = 50
%ndic tamanho do dicionario 