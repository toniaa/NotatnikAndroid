package com.tonia.notatnik;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NotatkiViewModel extends AndroidViewModel {
    private NotatkiRepository notatkiRepository;
    private LiveData<List<Notatka>> notatki;

    public NotatkiViewModel(@NonNull Application application) {
        super(application);
        notatkiRepository = new NotatkiRepository(application);
        notatki = notatkiRepository.getNotatki();
    }

    public LiveData<List<Notatka>> getNotatki() { return notatki; }

    public int count() {
        return notatkiRepository.count();
    }

    public LiveData<List<Notatka>> search(String query) { return notatkiRepository.search(query); }

    public long insert(Notatka notatka) {
        return notatkiRepository.insert(notatka);
    }

    public void delete(Notatka notatka) { notatkiRepository.delete(notatka); }

    public void update(Notatka notatka) { notatkiRepository.update(notatka); }
}
