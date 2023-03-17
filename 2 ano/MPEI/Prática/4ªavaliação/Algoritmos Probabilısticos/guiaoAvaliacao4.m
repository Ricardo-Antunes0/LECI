 %Guiao de avaliacao 4

userID = 0;

load('ValoresGRD.mat'); %carregar os valores criados em "Guiao4Leitura.m"

while (userID < 1 || userID > 943) %Validar o ID introduzido
    fprintf("Insert User ID (1 to 943): ");
    userID = input("");
end
userChoice = 0;

while(userChoice > 0 || userChoice < 5) %validar a opção escolhida pelo utilizador
    fprintf("\n1 - Your Movies\n2 - Get Suggestions\n3 - Search Title\n4 - Exit\n"); 
    fprintf("Select Choice: ");
    userChoice = input("");
    switch (userChoice)
        case 1
            listMovies(userID, Set, dic);
        case 2
            userGenre = 0;
            fprintf("\n1- Action, 2- Adventure, 3- Animation, 4- Children’s5- Comedy, 6- Crime, 7- Documentary, 8- Drama9- Fantasy, 10- Film-Noir, 11- Horror, 12- Musical13- Mystery, 14- Romance, 15- Sci-Fi, 16- Thriller17- War, 18- Western\n");
            while (userGenre < 1 || userGenre > 18) %validar a escolha de gênero do utilizador
                fprintf("Select Choice: ");
                userGenre = input("");
            end
            
            variavel1 = similaridade(userID, K, MinHashValue, Nu); %matriz com os valores de similaridade com cada utilizador
               
            [i, user2] = max(variavel1); %ver qual é o utilizador mais similar
              
            %funcao para imprimir sugestoes
            x = Set{user2}; %Filmes vistos pelo utilizador mais similar
            y = Set{userID}; %Filmes vistos pelo utilizador
            Printsugestoes(x, userGenre, dic, y)
        case 3
            userString = input("\nWrite a string: ",'s');
            searchMovie(userString, 3, dic, K, MinHashMovies, length(dic));
            
        case 4
            break;
            
    end
end


function listMovies(userID, Set, dic)
    userMovies = Set{userID}; %Dá o numero do filme visto pelo utilizador
    movies = strings(length(userMovies),1); %converter movies para um array de strings
    fprintf("\nWatched Movies: \n");
    for i = 1 : length(userMovies) %Percorrer todos os numeros do filme ja visto pelo utilizador
       fprintf("- %s\n", dic{userMovies(i),1}); %transformar o numero associado ao filme para o respectivo nome
    end
end


function var = similaridade(userID, K, MinHashValue, Nu)
    var=zeros(Nu,Nu); % array para guardar a similaridade
    h= waitbar(0,'Calculating');
    for n1= 1:Nu
        waitbar(n1/Nu,h);
        if(n1 ~= userID)
            var(n1) = (sum(MinHashValue(n1,:) == MinHashValue(userID, :))/K);
        end         
    end
    delete (h)
end

function Printsugestoes (x , userGenre, dic, y)
    tamanho = length(x); %numero de filmes vistos pelo utilizador mais similar
    valor = 0;
    for i = 1: tamanho
        if x(i) ~= y %se o utilizador nao viu o filme
            if dic{x(i), userGenre+2} == 1 %e se é do genero escolhido pelo utilizador
              if valor == 0
                  fprintf("\nMovies for you: \n");
              end
              fprintf("- %s\n", dic{x(i), 1}) %Imprimir uma sugestão
              valor = valor + 1;
            end
        end
    end 
    if valor == 0
        fprintf("\nThere are no suggestions");
    end
end

function searchMovie (userString, shinglesLength, dic, K, MinHashMovies, Nm)
    limite=0.99;
    
    %Criar MinHash da String introduzida pelo utilizador
    MinHashString=inf(1,K);
    for j=1:length(userString)-shinglesLength+1
        shingle=lower(char(userString(j:j+shinglesLength-1)));
        h=zeros(1,K);
        for m=1:K
            shingle=[shingle num2str(m)];
            h(m)=DJB31MA(shingle,127);
        end
        MinHashString(1,:)=min([MinHashString(1,:);h]);
    end
    %
    
    %Armazenar os Titulos com distancia de Jaccard inferior ou igual a 0.99
    movies={};
    for n=1 : Nm
        distancia=1-sum(MinHashString(1,:) == MinHashMovies(n,:))/K;
        if distancia <= limite
            movies{end+1,1}= dic{n,1};
            movies{end,2}= distancia;
        end
    end
    %
    
    %Imprimir os resultados obtidos
    if length(movies) == 0
        fprintf("We couldn't find movies with that title\n");
    else
        movies = sortrows(movies, 2); %ordenar por ordem crescente com base na segunda coluna
        for i = 1 : min(height(movies), 5) %minimo numero entre o numero de filmes na matriz movies ou 5
            fprintf("- %s (Jaccard ~%.2f)\n", movies{i,1}, movies{i,2}); 
        end
    end
    %
end



