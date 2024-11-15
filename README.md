```
Programação Orientada aos Objetos
2024 / 2025
Projeto
POOFS
```
**Prazo de entrega:** 08 / 12 /2024
**Plataforma:** Inforestudante **Cotação:** 7 valores

```
Nota : A fraude denota uma grave falta de ética e constitui um comportamento não admissível.
Situações de fraude serão severamente penalizadas, de acordo com os regulamentos e legislação em
vigor.
```
Pretende-se que desenvolva uma aplicação de gestão financeira com o nome **_POO Financial
Services_** (POOFS), que permita a uma empresa registar as faturas que emite aos seus clientes
e exportar essa informação para submissão no portal das Finanças. Esta aplicação deverá
permitir a emissão de faturas que incluam produtos alimentares e de farmácia. Para registar
uma fatura, os utilizadores devem indicar o número da fatura, o cliente, a data e a lista dos
produtos que compõem a fatura. Os clientes são caracterizados pelo seu nome, número de
contribuinte e localização (Portugal Continental, Madeira ou Açores). Os produtos
alimentares são caracterizados pelo código, nome, descrição, quantidade, o valor unitário
(sem IVA) e se é um produto biológico. Os produtos alimentares são taxados de forma
diferente, dividindo-se em três tipos: taxa reduzida, taxa intermédia e taxa normal. Os
produtos alimentares de taxa reduzida têm uma ou mais certificações (máximo 4): ISO22000,
FSSC22000, HACCP, GMP. Os produtos alimentares de taxa intermédia podem pertencer a
uma das categorias: congelados, enlatados e vinho. Aos restantes produtos alimentares
aplica-se a taxa normal. Os produtos de farmácia são caracterizados pelo código, nome,
descrição, quantidade e valor unitário (sem IVA). Os produtos de farmácia podem ter ou não
prescrição. Os produtos de farmácia com prescrição devem indicar o médico que prescreveu
o medicamento. Os produtos de farmácia normais, sem prescrição médica, devem pertencer
a uma das seguintes categorias: beleza, bem-estar, bebés, animais ou outro.

A tabela seguinte apresenta a taxa de imposto (IVA) associada a cada produto, de acordo com
a localização do cliente. Os valores estão expressos em percentagem.

```
Tipo Continente Madeira Açores Extras
Alimentação
Taxa reduzida 6 5 4 - 1 se 4 certificações
Taxa intermédia 13 12 9 +1 se da categoria vinho
Taxa normal 23 22 16
Farmácia
Com prescrição 6 5 4
Normal 23 23 23 - 1 se da categoria animais
```
Além das diferentes taxas de imposto por tipo de produto e a localização do cliente, em
algumas situações há extras que aumentam ou diminuem o imposto sobre o produto:


- Um produto de taxa reduzida com as 4 certificações possíveis recebe uma redução de 1%
    na taxa de imposto.
- Um produto de taxa intermédia que seja da categoria “vinho” recebe um aumento de 1%
    na taxa de imposto.
- Um produto de farmácia normal que seja da categoria “animais” recebe uma redução de
    1% na taxa de imposto.

Além desses extras na taxa de imposto, os produtos alimentares que sejam biológicos têm
um desconto de 10% no valor do imposto.

Funcionalidades da aplicação a desenvolver:

- **Criar e editar cliente** : solicitar ao utilizador os dados do cliente.
- **Lista de clientes** : listar os clientes com todos os seus dados.
- **Criar e editar faturas:** deverá pedir ao utilizador os dados da fatura, bem como todos os
    produtos incluídos.
- **Listar faturas:** listar as faturas, apresentando para cada uma o seu número, cliente,
    localização do cliente, número de produtos, valor total sem IVA e valor total com IVA.
- **Visualizar fatura:** apresentar o número da fatura, os dados do cliente e, em seguida, listar
    os produtos da fatura. Para cada produto, apresentar os dados relevantes do produto, o
    valor total sem IVA, a taxa do IVA, o valor do IVA e o valor total com IVA. Após esta lista,
    apresentar da fatura o total sem IVA, o valor total do IVA e o valor total com IVA.
- **Importar faturas** : importar faturas contidas num ficheiro de texto.
- **Exportar as faturas** : exportar faturas para um ficheiro de texto.
- **Estatísticas:** apresentar ao utilizador os seguintes dados:
    o Número de faturas.
    o Número de produtos.
    o Valor total sem IVA.
    o Valor total do IVA.
    o Valor total com IVA.

A aplicação deve ser disponibilizada com um ficheiro de texto contendo dados de, pelo menos,
três clientes em localizações diferentes, quatro faturas e, no mínimo, dois produtos de cada
tipo, para permitir demonstrar todas as funcionalidades. A estrutura do ficheiro de texto
deverá ser definida pelos estudantes, de forma a facilitar a edição e simplificar o seu _parsing_
pela aplicação.

A aplicação deve ser entregue sem faturas ou clientes pré-carregados. No entanto, durante a
execução do programa, antes de encerrar, o programa deve guardar todos os dados num
ficheiro de objetos (criando-o, se não existir). Ao iniciar, caso este ficheiro exista, o programa
deve carregar esses dados.


# Implementação

A aplicação deverá ser implementada na linguagem Java e ter em conta os seguintes aspetos:

1. Elaboração de um diagrama de classes (UML) antes de iniciar a implementação, para
    prever a estrutura do projeto.
2. Serão penalizadas soluções que não sigam os princípios da programação orientada aos
    objetos e os conceitos de herança e polimorfismo.
3. A utilização de _instanceof_ , _class.getName()_ ou de métodos semelhantes será penalizada.
4. As soluções devem ter em consideração boas práticas de programação.
5. Cada classe deverá gerir internamente os seus dados, pelo que deverá cuidar da proteção
    das suas variáveis e métodos.
6. Cada classe deverá ser responsável por uma tarefa ou objetivo específico, não lhe
    devendo ser atribuídas funções indevidas.
7. Utilize a _keyword static_ apenas quando tal se justifique e não para contornar erros do
    compilador.
8. Comente as classes e os métodos públicos segundo o formato Javadoc. Isto permitirá
    gerar automaticamente uma estrutura de ficheiros HTML. Comente o restante código
    sempre que a leitura dos algoritmos não seja óbvia.
9. Evite o uso abusivo de variáveis e métodos _public_.
10. Na escolha de nomes para variáveis, classes e métodos devem ser seguidas as convenções
    adotadas na linguagem Java.
11. Na organização das classes deverá ser evitada a redundância do código.

# Entrega

O trabalho deve ser entregue no Inforestudante até ao dia **8 de dezembro de 202 4**. O projeto
deve ser realizado em grupos de dois estudantes da mesma turma prática.

Devem ser entregues os seguintes ficheiros ( **sem compressão** ):

- Diagrama de classes em UML (pdf).
- Todas as classes .java e ficheiro de texto para teste.
- Ficheiro ZIP com o Javadoc (ZIP).
- Relatório (pdf).

# Avaliação do trabalho

Para a avaliação do trabalho são considerados fatores de dois tipos:

- Caixa preta (tal como é percecionado pelo utilizador):
    o Conjunto de funcionalidades implementadas.
    o Robustez do programa.
    o Qualidade da interface.
- Caixa branca (a forma como está construído):
    o Qualidade das soluções técnicas encontradas para os problemas.
    o Estruturação do código.
    o Qualidade dos comentários.


**Nota:** Não se aceitam trabalhos que apresentem erros de compilação no momento da defesa.
Todos os erros de execução deverão ser detetados e tratados.

# Defesa final do trabalho

O trabalho será defendido através de uma discussão presencial. Os alunos deverão inscrever-
se num horário de defesa no Inforestudante.

# MUITO IMPORTANTE

Os trabalhos serão comparados (tanto entre os trabalhos da disciplina como com código
disponível na Internet), no sentido de detetar eventuais fraudes por cópia. Nos casos em que
se verifique que houve cópia de trabalho total ou parcial, os grupos envolvidos terão os
projetos anulados, reprovando à disciplina. Serão aplicadas as regras da Universidade de
Coimbra relativamente ao plágio.

