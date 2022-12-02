<template>
  <div class="grid">
          <div class="col-12">
              <div class="card">
                  <Toast/>
                  <div class="grid">
                    <div class="col-12">
                        <div class="card">
                            <h5>Adicione a imagem</h5>
                            <FileUpload name="demo[]" url="./upload.php" @upload="onUpload" :multiple="true" accept="image/*" :maxFileSize="1000000"/>
                        </div>
                    </div>
                </div>
              </div>
          </div>
      </div>
</template>

<script>
export default {

}
</script>

<style>

</style>