<template>
    <Toast/>
    <div class="corpo">
        <div class="container">
            <div class="header">
                <h2>Novo Produto</h2>
            </div>
    
            <form id="form" class="form-carro" @submit.prevent="salvar()">
                <div class="form-control">
                    <label for="nome">Nome do Produto: </label>
                    <InputText type="text" v-model="produto.nome"/>
                </div>
                <div class="form-control">
                    <label for="">Descrição: </label>
                    <InputText type="text" v-model="produto.descricao"/>
                </div>
                <div class="form-control">
                    <label for="">Valor do Produto: </label>
                    <InputText type="number" step=".01" v-model="produto.valor"/>
                </div>
                <div class="form-control">
                    <label for="">Classificação: </label>
                    <InputText type="number" v-model="produto.classificacao"/>
                </div>
                <div class="form-control-dropdown">
                    <label for="">Marca do Produto:</label>
                    <Dropdown v-model="produto.marca" :options="marcas" optionLabel="nome" placeholder="Selecione a marca" />
                </div>
                
                <Button type="submit" label="Salvar"/>
                <Button label="Voltar" class="p-button-link" @click="voltar()"/>
            </form>
        </div>
    </div>
    </template>
      
      <script>
      import axios from 'axios'
      export default {
          data() {
              return {
                produto: {},
                marcas: [], 
              }
          },
          mounted() {
            const id = this.$route.params.id
            if (id) {
                axios.get(`http://localhost:8080/produto/${id}`).then(resp => this.produto = resp.data)
                .catch(error => {
                    alert(error)
                    this.$router.push("/produto")
                })
            }
            else {
                this.produto = {}
            }
            axios
            .get('http://localhost:8080/marca')
            .then(resp => {
               this.marcas = resp.data
            })
        },
        methods: {
            salvar() {
                axios.post('http://localhost:8080/produto', this.produto)
                .then(() => this.$toast.add({severity:'success', summary: 'Successo!', detail: 'Registro gravado.', life: 4000}))
                .catch(error => this.$toast.add({severity:'error', summary: `Problema na gravação ${error}`}))
                this.limpaForm()
            },
            voltar() {
                this.$router.go(-1)
            },
            limpaForm() {
              this.produto = {
                nome: '',
                valor: ''
              }
            },
        }
    }
    
      </script>
      
      <style>
      .corpo {
        width: 100%;
        min-height: 90vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    
    .container {
      background-color: #fafafa;
      border-radius: 10px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
      width: 400px;
      max-width: 100%;
      overflow: hidden;
    }
    
    .header {
      background-color: #eee;
      padding: 20px;
    }
    
    .form-carro {
      padding: 20px;
    }
    
    .form-control {
      margin-bottom: 10px;
      padding-bottom: 20px;
      position: relative;
    }
    
    .form-control-dropdown {
      margin-bottom: 30px;
      position: relative;
      border-radius: 0;
      box-shadow: 0;
    }
    
    .form-control label {
      display: inline-block;
      margin-bottom: 10px;
    }
    
    .form-carro .p-inputtext {
     
      display: block;
      border-radius: 10px;
      font-size: 14px;
      width: 100%;
      padding: 10px;
    }
    
    .form-carro .p-button {
      font-size: 14px;
      width: 100%;
      border-radius: 10px;
      padding: 10px;
      margin-bottom: 5px;
    }
    
    .form-carro .p-dropdown {
        margin-top: 10px;
        width: 100%;
        border: 0;
    }
    </style>