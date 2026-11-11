# Java Collections Framework

Respostas sobre o Java Collections Framework (JCF), as interfaces `List`, `Set` e `Map` e as implementações de `Set`.

## Sumário

1. [O que é o Java Collections Framework](#1-o-que-é-o-java-collections-framework)
2. [Diferenças entre List, Set e Map](#2-diferenças-entre-list-set-e-map)
3. [HashSet, LinkedHashSet e TreeSet](#3-hashset-linkedhashset-e-treeset)

---

## 1. O que é o Java Collections Framework

O **Java Collections Framework** é uma arquitetura unificada, presente no pacote `java.util`, para representar e manipular grupos de objetos. Ele é formado por três partes:

| Parte | O que é | Exemplos |
|---|---|---|
| **Interfaces** | Definem o comportamento (o "contrato") de cada tipo de coleção | `Collection`, `List`, `Set`, `Queue`, `Deque`, `Map` |
| **Implementações** | Classes concretas que implementam as interfaces | `ArrayList`, `LinkedList`, `HashSet`, `TreeSet`, `HashMap`, `ArrayDeque` |
| **Algoritmos** | Métodos utilitários que operam sobre coleções | `Collections.sort()`, `Collections.shuffle()`, `Collections.max()`, `Collections.unmodifiableList()` |

### Hierarquia simplificada

```
Iterable
  └── Collection
        ├── List   (ArrayList, LinkedList)
        ├── Set    (HashSet, LinkedHashSet, TreeSet)
        └── Queue  (PriorityQueue, ArrayDeque*)

Map  (HashMap, LinkedHashMap, TreeMap)   ← não herda de Collection
```

\* `ArrayDeque` implementa `Deque`, que estende `Queue`.

### Benefícios em relação a arrays tradicionais

| Aspecto | Array | Collections Framework |
|---|---|---|
| **Tamanho** | Fixo, definido na criação | Dinâmico: cresce e encolhe conforme a necessidade |
| **Operações prontas** | Só acesso por índice e `length` | `add`, `remove`, `contains`, `size`, `isEmpty`, `clear`... |
| **Estruturas de dados** | Apenas sequência indexada | Listas, conjuntos, mapas, filas, pilhas, árvores |
| **Tipagem** | Verificada, mas sem recursos genéricos | Genéricos (`List<String>`) com checagem em tempo de compilação |
| **Trocar a implementação** | Reescrever o código | Basta trocar a classe: `List<String> l = new ArrayList<>()` → `new LinkedList<>()` |
| **Algoritmos** | Escritos à mão (ou `Arrays.sort`) | `Collections.sort`, `binarySearch`, `reverse`, `shuffle`... |
| **Integração com streams** | Precisa de `Arrays.stream` | `.stream()` direto, com `filter`, `map`, `collect` |
| **Percorrer** | `for` por índice | `for-each`, `Iterator`, `forEach` |

**Principais benefícios:**

- **Menos código e menos bugs:** a estrutura de dados já vem pronta, testada e otimizada.
- **Flexibilidade:** programar para a interface (`List`, `Set`, `Map`) permite trocar a implementação sem mexer no resto do programa.
- **Reuso e legibilidade:** todo programador Java conhece essa API.
- **Desempenho:** as implementações usam algoritmos eficientes (tabelas hash, árvores rubro-negras, arrays redimensionáveis).

**Quando o array ainda é melhor:** com tamanho fixo e conhecido, ou com tipos primitivos (`int[]`, `double[]`). As coleções guardam apenas objetos, então um `List<Integer>` faz *boxing* e ocupa mais memória que um `int[]`.

```java
// Array: tamanho fixo
String[] nomes = new String[3];
nomes[0] = "Ana";            // não dá para adicionar um 4º elemento

// Collection: tamanho dinâmico
List<String> lista = new ArrayList<>();
lista.add("Ana");
lista.add("Bruno");
lista.remove("Ana");
```

---

## 2. Diferenças entre List, Set e Map

### Comparação de comportamento

| Característica | `List` | `Set` | `Map` |
|---|---|---|---|
| **Armazena** | Elementos | Elementos | Pares **chave → valor** |
| **Duplicatas** | Permite | **Não** permite | Chaves **únicas**; valores podem repetir |
| **Ordem** | Mantém a ordem de inserção | Depende da implementação | Depende da implementação |
| **Acesso por índice** | Sim (`get(i)`) | Não | Não (acesso pela **chave**) |
| **Herda de `Collection`** | Sim | Sim | **Não** |
| **Implementações comuns** | `ArrayList`, `LinkedList` | `HashSet`, `LinkedHashSet`, `TreeSet` | `HashMap`, `LinkedHashMap`, `TreeMap` |

### `List`: sequência ordenada, com repetição

Use quando a **posição** importa ou quando repetições são válidas.

- Carrinho de compras (o mesmo produto pode aparecer duas vezes).
- Histórico de ações do usuário, na ordem em que ocorreram.
- Resultado de uma consulta que precisa ser exibido em ordem.

```java
List<String> carrinho = new ArrayList<>();
carrinho.add("Caderno");
carrinho.add("Caneta");
carrinho.add("Caneta");           // permitido
System.out.println(carrinho.get(1)); // Caneta
```

### `Set`: conjunto sem repetição

Use quando o requisito principal é **unicidade** e a posição não importa.

- Tags de uma postagem (sem repetir).
- Palavras distintas de um texto.
- Controle de "já visitei / já processei" (ex.: IDs já tratados).

```java
Set<String> tags = new HashSet<>();
tags.add("java");
tags.add("collections");
boolean novo = tags.add("java");  // false: já existia, não é inserido
```

### `Map`: associação chave → valor

Use quando você precisa **buscar um valor a partir de uma chave**.

- Cadastro de usuários por e-mail (a chave impede e-mails repetidos).
- Contador de ocorrências (palavra → quantidade).
- Dicionário ou tabela de configuração (nome da opção → valor).

```java
Map<String, Integer> contagem = new HashMap<>();
for (String palavra : palavras) {
    contagem.merge(palavra, 1, Integer::sum);   // conta quantas vezes cada palavra aparece
}
System.out.println(contagem.get("java"));
```

### Como escolher

| Pergunta | Escolha |
|---|---|
| A ordem/posição importa e posso ter repetidos? | `List` |
| Preciso garantir elementos únicos? | `Set` |
| Preciso encontrar algo rapidamente por um identificador? | `Map` |

---

## 3. HashSet, LinkedHashSet e TreeSet

Todas implementam `Set` (sem elementos duplicados), mas usam estruturas internas diferentes.

| | `HashSet` | `LinkedHashSet` | `TreeSet` |
|---|---|---|---|
| **Estrutura interna** | Tabela hash (usa um `HashMap`) | Tabela hash + lista duplamente ligada | Árvore rubro-negra (usa um `TreeMap`) |
| **Ordem dos elementos** | **Nenhuma garantida** | **Ordem de inserção** | **Ordenada** (ordem natural ou `Comparator`) |
| **Busca** (`contains`) | O(1) em média | O(1) em média | O(log n) |
| **Inserção** (`add`) | O(1) em média | O(1) em média | O(log n) |
| **Remoção** (`remove`) | O(1) em média | O(1) em média | O(log n) |
| **Uso de memória** | Menor | Médio (HashSet + ponteiros extras) | Médio/alto (um nó com vários ponteiros por elemento) |
| **Aceita `null`** | Sim (um) | Sim (um) | Não (com ordem natural) |
| **Como compara elementos** | `equals()` e `hashCode()` | `equals()` e `hashCode()` | `compareTo()` ou `Comparator` |

### Ordem dos elementos

- **`HashSet`:** a ordem depende do hash dos elementos e pode mudar quando o conjunto cresce. Não se deve contar com nenhuma ordem.
- **`LinkedHashSet`:** percorre os elementos na ordem em que foram inseridos. Reinserir um elemento já existente não altera a posição dele.
- **`TreeSet`:** mantém sempre os elementos ordenados. Os elementos precisam implementar `Comparable` ou o conjunto precisa receber um `Comparator`. Também oferece operações de navegação: `first()`, `last()`, `floor()`, `ceiling()`, `headSet()`, `tailSet()`.

```java
List<String> dados = Arrays.asList("banana", "abacaxi", "caju", "banana", "maçã");

System.out.println(new HashSet<>(dados));        // ordem imprevisível
System.out.println(new LinkedHashSet<>(dados));  // [banana, abacaxi, caju, maçã]
System.out.println(new TreeSet<>(dados));        // [abacaxi, banana, caju, maçã]
```

### Complexidade

- **`HashSet` e `LinkedHashSet`:** busca, inserção e remoção em **O(1) em média**. No pior caso, com muitas colisões de hash, a performance cai (desde o Java 8, os buckets muito cheios viram árvores, limitando o pior caso a O(log n)). A qualidade do `hashCode()` influencia diretamente o desempenho.
- **`TreeSet`:** busca, inserção e remoção em **O(log n)** de forma garantida, porque a árvore é sempre balanceada. É mais lento que o hash em média, mas tem desempenho previsível e mantém a ordenação.

### Uso de memória

Os valores exatos dependem da JVM, mas a relação geral é esta:

- **`HashSet`:** o mais econômico dos três. Cada elemento fica num nó pequeno (hash, chave, valor interno e próximo) dentro da tabela de buckets.
- **`LinkedHashSet`:** gasta mais que o `HashSet`, porque cada nó guarda **dois ponteiros extras** (anterior e próximo) para manter a ordem de inserção.
- **`TreeSet`:** cada elemento ocupa um nó com chave, valor interno, filhos esquerdo e direito, pai e a cor do nó. Em geral é comparável ou maior que o `LinkedHashSet`. Em compensação, não tem a tabela de buckets nem a folga do fator de carga.

### Quando usar cada um

| Situação | Escolha |
|---|---|
| Só preciso garantir unicidade, com a melhor performance | `HashSet` |
| Preciso de unicidade **e** de manter a ordem de inserção (ex.: palavras únicas na ordem em que aparecem) | `LinkedHashSet` |
| Preciso dos elementos **ordenados** ou de buscas por faixa (ex.: ranking, nomes em ordem alfabética) | `TreeSet` |

---

## Resumo

- O **JCF** oferece estruturas de dados prontas, dinâmicas e intercambiáveis, no lugar dos arrays de tamanho fixo.
- **`List`** guarda sequências com repetição, **`Set`** guarda elementos únicos e **`Map`** associa chaves a valores.
- **`HashSet`** é o mais rápido e leve, **`LinkedHashSet`** preserva a ordem de inserção e **`TreeSet`** mantém os elementos ordenados ao custo de O(log n).
