/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.afifpermana.peminjaman.services.service;

import com.afifpermana.peminjaman.services.entity.Peminjaman;
import com.afifpermana.peminjaman.services.repository.PeminjamanRepository;
import com.afifpermana.peminjaman.services.vo.Anggota;
import com.afifpermana.peminjaman.services.vo.Buku;
import com.afifpermana.peminjaman.services.vo.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Apip
 */
@Service
public class PeminjamanService {
    @Autowired
    private PeminjamanRepository peminjamanRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    public Peminjaman savePeminjaman(Peminjaman peminjaman){
        return peminjamanRepository.save(peminjaman);
    }
    
    public ResponseTemplateVO getPeminjaman(Long peminjamanId){
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Peminjaman peminjaman = peminjamanRepository.findByPeminjamanId(peminjamanId);
        Anggota anggota = restTemplate.getForObject("http://localhost:8100/anggota/" 
              + peminjaman.getAnggotaId(), Anggota.class);
        Buku buku = restTemplate.getForObject("http://localhost:8200/buku/" 
                + peminjaman.getBukuId(), Buku.class);
        vo.setPeminjaman(peminjaman);
        vo.setAnggota(anggota);
        vo.setBuku(buku);
        return vo;
    }
}
