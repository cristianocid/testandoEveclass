Feature: Selecionar Passagem

  Scenario: Selecionar Passagem com Sucesso
    Given que acesso o site Blazedemo
    When seleciono a origem como "São Paolo" e destino "Berlin"
    When clico em Procurar Voo
    Then exibe a frase indicando voo "São Paolo" e "Berlin"