<template>
	  <div class="grid">
          <div class="col-12">
              <div class="card">
                  <Toast/>
                  <DataTable ref="dt" :value="carrinho" v-model:selection="selectedCarrinho" dataKey="id" :paginator="true" :rows="10" :filters="filters"
                              paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink "
                              currentPageReportTemplate="Showing {first} to {last} of {totalRecords} produtos" responsiveLayout="scroll">
                            <template #header>
                                <div class="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
                                    <h5 class="m-0">Produtos no carrinho</h5>
                                    <span class="block mt-2 md:mt-0 p-input-icon-left">
                                        <i class="pi pi-search" />
                                        <InputText v-model="filters['global'].value" placeholder="Search..." />
                                    </span>
                                </div>
                            </template>
  
                      <Column header="Imagem" headerStyle="width:1%; min-width:10rem;">
                        <template #body="slotProps">
                            <img :src="('images/bebidas/' + slotProps.data.produto.imagem)" :alt="slotProps.produto.nome" class="shadow-2" width="100" />
                        </template>
                      </Column>
                      <Column field="produto.nome" header="Nome" :sortable="true" headerStyle="width:14%; min-width:10rem;"></Column>
                      <Column field="produto.valor" header="Valor" :sortable="true" headerStyle="width:1%; min-width:10rem;"></Column>
					  <Column field="valorT" header="Valor total" headerStyle="width:1%; min-width:10rem;"></Column>
                      <Column headerStyle="min-width:10rem;">
                          <template #body="{data}">
                              <Button icon="pi pi-image" class="p-button-rounded p-button-primary mr-2" @click="salvarImagem(data)" />
                              <Button icon="pi pi-pencil" class="p-button-rounded p-button-warning mr-2" @click="editar(data)" />
                              <Button icon="pi pi-trash" class="p-button-rounded p-button-danger mt-2" @click="confirmDeleteProduto(data)" />
                          </template>
                      </Column>
                    </DataTable>
                    <Dialog v-model:visible="deleteProdutoDialog" :style="{width: '450px'}" header="Confirmação" :modal="true">
                        <div class="flex align-items-center justify-content-center">
                            <i class="pi pi-exclamation-triangle mr-3" style="font-size: 2rem" />
                            <span v-if="produtos">Deseja realmente excluir o produto <b>{{produto.nome}}</b>?</span>
                        </div>
                        <template #footer>
                            <Button label="Não" icon="pi pi-times" class="p-button-text" @click="deleteProdutoDialog = false"/>
                            <Button label="Sim" icon="pi pi-check" class="p-button-text" @click="excluir(produto)" />
                        </template>
                    </Dialog>
              </div>
          </div>
		</div>
</template>

<script>
import {FilterMatchMode} from 'primevue/api';
import axios from 'axios'

export default {
	data() {
		return {
            carrinho: [],
			filters: {},
		}
	},
	created() {
		this.initFilters();
	},
	methods: {
        load() {
            axios
            .get('http://localhost:8080/carrinho')
            .then(resp => {
                this.carrinho = resp.data
            })
        },	
      excluir(carrinho) {
			this.deleteProductDialog = false;
            const id = carrinho.id
            axios
                .delete(`http://localhost:8080/carrinho/${id}`)
                .then(this.load(),
				location.reload())
                .catch(error => alert(error))
                
        },
		initFilters() {
            this.filters = {
                'global': {value: null, matchMode: FilterMatchMode.CONTAINS},
            }
        }
	},
	mounted() {
		this.load();
	}
}
</script>

<style scoped lang="scss">
@import '../assets/demo/badges.scss';
</style>