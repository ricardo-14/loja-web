<template>
	  <div class="grid">
          <div class="col-12">
              <div class="card">
                  <Toast/>
                  <DataTable ref="dt" :value="carrinhos" v-model:selection="selectedCarrinhos" dataKey="id" :paginator="true" :rows="10" :filters="filters"
                              paginatorTemplate=""
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
  
                      <Column header="" headerStyle="width:1%; min-width:10rem;">
                        <template #body="slotProps">
                            <img :src="('images/bebidas/' + slotProps.data.produto.imagem)" :alt="slotProps.data.produto.nome" class="shadow-2" width="100" />
                        </template>
                      </Column>
                      <Column field="produto.nome" header="Nome" headerStyle="width:14%; min-width:10rem;"></Column>
                      <Column field="quantidade" header="Quantidade" :sortable="true" headerStyle="width:14%; min-width:10rem;"></Column>
                      <Column field="produto.valor" header="Valor" :sortable="true" headerStyle="width:1%; min-width:10rem;"></Column>
                      <Column headerStyle="min-width:10rem;">
                          <template #body="{data}">
                              <Button icon="pi pi-pencil" class="p-button-rounded p-button-warning mr-2" @click="data"/>
                              <Button icon="pi pi-trash" class="p-button-rounded p-button-danger mt-2" @click="data"/>
                          </template>
                      </Column>
                      <ColumnGroup type="footer">
                        <Row>
                            <Column footer="Valor total: " :colspan="3" footerStyle="text-align:right"/>
                            <Column :footer=55.50 />
                        </Row>
                      </ColumnGroup>
                        <template #paginatorend>
                            <br>
                            <Button label="Realizar pagamento" icon="pi pi-chevron-right" iconPos="right" />
                        </template>
        
                    </DataTable>
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
            carrinhos: [],
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
                this.carrinhos = resp.data
            })
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